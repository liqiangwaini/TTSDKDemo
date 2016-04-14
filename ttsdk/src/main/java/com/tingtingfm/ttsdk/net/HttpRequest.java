package com.tingtingfm.ttsdk.net;

import android.os.Handler;

import com.tingtingfm.ttsdk.utils.BaseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
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

        handler = new Handler();
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
                                + BaseUtils.UrlEncodeUnicode(p.getValue()));
                    } else {
                        paramBuffer.append("&" + p.getName() + "="
                                + BaseUtils.UrlEncodeUnicode(p.getValue()));
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
                    message = inputStreamToString(connection.getInputStream());

                    // 设置回调函数
                    final Response responseInJson = stringToResponse(message);
                    if (responseInJson.hasError()) {
                        handleNetworkError(responseInJson.getError());
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
                    handleNetworkError("网络异常");
                }
            }
        } catch (final IllegalArgumentException e) {
            handleNetworkError("网络异常");
        } catch (final UnsupportedEncodingException e) {
            handleNetworkError("网络异常");
        } catch (final JSONException e) {
            handleNetworkError("解析异常");
        } catch (final IOException e) {
            handleNetworkError("网络异常");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    void handleNetworkError(final String errorMsg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                requestCallback.onCancel();
                requestCallback.onFail(errorMsg);
            }
        });
    }

    String inputStreamToString(final InputStream is)
            throws IOException {
        String message = "";
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                is, "gb2312"));

        String line = null;
        while ((line = bufReader.readLine()) != null) {
            message += line + "\n";
        }

        return message;
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