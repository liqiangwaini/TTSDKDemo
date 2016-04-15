package com.tingtingfm.ttsdk.helper;

import com.tingtingfm.ttsdk.utils.BaseUtils;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lenovo on 2016/4/15.
 */
final class AnalyticsTask {
    private static String ANALY_INSTALL = "analy_install";
    private static String ANALY_ACTIVE = "analy_active";
    // 渠道地址
    final static String CHANNEL_URL_DEV = "http://api.union.dev.ting-ting.cn";
    final static String CHANNEL_URL_RELEASE = "http://api.union.tingtingfm.com";

    final static String INSTALLURL = CHANNEL_URL_DEV + "/record/install";
    final static String ACTIVIEURL = CHANNEL_URL_DEV + "/record/active";
    final static String CONNECTURL = CHANNEL_URL_DEV + "/record/connect";


    public static void install() {
        if (!ConfigurationManager.getInstance().getStatusForTag(ANALY_INSTALL)) {//是否已经安装
            request(ANALY_INSTALL, new AnalyticsRequest(INSTALLURL));
        }
    }

    public static void active() {
        if (!ConfigurationManager.getInstance().getStatusForTag(ANALY_ACTIVE)) {//是否已经激活
            request(ANALY_ACTIVE, new AnalyticsRequest(ACTIVIEURL));
        }
    }

    public static void requestNet() {
        request("", new AnalyticsRequest(CONNECTURL));
    }

    public static void request(final String type, final AnalyticsRequest request) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                HttpURLConnection connection = null;
                try {
                    url = new URL(request.url);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(30000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setRequestMethod("POST");

                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(request.getRequestData().toString().getBytes());

                    final int statusCode = connection.getResponseCode();
                    if (statusCode == HttpURLConnection.HTTP_OK) {
                        if (ANALY_INSTALL.equals(type) || ANALY_ACTIVE.equals(type)) {
                            String message = BaseUtils.inputStreamToString(connection.getInputStream());

                            JSONObject object = new JSONObject(message);
                            if (object.has("data")) {
                                object = object.getJSONObject("data");
                                if (object.getInt("succ") == 1) {
                                    ConfigurationManager.getInstance().setStatus(type, true);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
