package com.tingtingfm.ttsdk.net;

import android.os.Handler;
import android.os.Looper;

import com.tingtingfm.ttsdk.helper.ErrorCode;
import com.tingtingfm.ttsdk.utils.BaseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;


public final class HttpRequest implements Runnable {
    private RequestCallback requestCallback = null;
    private List<RequestParameter> parameter = null;
    private String url = null;
    private String tag = "";
    protected Handler handler;
    private HttpURLConnection connection = null;

    public HttpRequest(final String data, final List<RequestParameter> params,
                       final RequestCallback callBack) {

        url = data;
        this.parameter = params;
        requestCallback = callBack;

        handler = new Handler(Looper.myLooper());
    }

    public HttpRequest(final String tag, final String data, final List<RequestParameter> params,
                       final RequestCallback callBack) {
        this(data, params, callBack);
        this.tag = tag;
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

    @Override
    public void run() {
        String newUrl = url;
        String message = "";
        try {
            if (requestCallback != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        requestCallback.onStart();
                    }
                });
            }

            // 添加参数
            final StringBuffer paramBuffer = new StringBuffer();
            if ((parameter != null) && (parameter.size() > 0)) {
                for (final RequestParameter p : parameter) {
                    if (paramBuffer.length() == 0) {
                        paramBuffer.append(p.getName() + "="
                                + URLEncoder.encode(p.getValue(), "UTF-8"));
                    } else {
                        paramBuffer.append("&" + p.getName() + "="
                                + URLEncoder.encode(p.getValue(), "UTF-8"));
                    }
                }

                newUrl += "?" + paramBuffer.toString();
            }

            URL url = new URL(newUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);
            // 发送请求
            connection.connect();
            // 获取状态

            if ((requestCallback != null)) {
                final int statusCode = connection.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    message = BaseUtils.inputStreamToString(connection.getInputStream());

                    // 设置回调函数
                    final Response responseInJson = stringToResponse(message);
                    if (responseInJson.hasError()) {
                        handleNetworkError(responseInJson.getErron(), responseInJson.getError());
                    } else {
                        final Object o = requestCallback.parseNetworkResponse(responseInJson.getData());
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                requestCallback.onCancel();
                                requestCallback.onSuccess(o);
                            }

                        });
                    }
                } else {
                    handleNetworkError(statusCode, "network error");
                }
            }
        } catch (final IllegalArgumentException e) {
            handleNetworkError(ErrorCode.errorcode_internal_error, e.getMessage());
        } catch (final UnsupportedEncodingException e) {
            handleNetworkError(ErrorCode.errorcode_internal_error, e.getMessage());
        } catch (final JSONException e) {
            handleNetworkError(ErrorCode.errorcode_internal_error, e.getMessage());
        } catch (final IOException e) {
            handleNetworkError(ErrorCode.errorcode_internal_error, e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    void handleNetworkError(final int code, final String errorMsg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                requestCallback.onCancel();
                requestCallback.onFail(code, errorMsg);
            }
        });
    }

    Response stringToResponse(String message) throws JSONException {
        Response response = new Response();
        JSONObject object = new JSONObject(message);
        response.setErron(object.getInt("errno"));
        response.setError(object.getString("error"));
        response.setData(object.getJSONObject("data").toString());

        return response;
    }
}