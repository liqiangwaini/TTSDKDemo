package com.tingtingfm.ttsdk.helper;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/15.
 */
public class AnalyticsRequest {
    /**
     * 客户端平台，目前支持：iphone,ipad,android,androidpad
     */
    String client_platform = "android";
    /**
     * android版本应该以android_开头
     */
    String client;
    /**
     * 版本号，如:v_1.3
     */
    String version;
    /**
     * 推广的产品编号
     */
    int product_id = 54;
    /**
     * 渠道编号，必须是小渠道编号
     */
    String scid;
    /**
     * {接口地址}#{client}#{client_platform}#{product_id}#{scid}# 6D12F0724b17AA69
     */
    String hash;

    String url;

    public AnalyticsRequest(String url) {
        this.url = url;
        client = "android_" + getDeviceId();
        scid = ConfigurationManager.getInstance().getScid();
        version = "v_" + VersiconConfig.version;
        hash = hashCode(url);
    }

    private String hashCode(String url) {
        StringBuffer sb = new StringBuffer();
        sb.append(url.substring(AnalyticsTask.CHANNEL_URL_DEV.length()));
        sb.append("#");
        sb.append(client);
        sb.append("#");
        sb.append(client_platform);
        sb.append("#");
        sb.append(product_id);
        sb.append("#");
        sb.append(scid);
        sb.append("#");
        sb.append(version);
        sb.append("#");
        sb.append("6D12F0724b17AA69");
        return encryption(sb.toString());
    }

    private String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset] & 0xff;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    private String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) ConfigurationManager.getInstance()
                .getApplication().getSystemService(Context.TELEPHONY_SERVICE);

        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }

        return "";
    }

    public StringBuffer getRequestData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_platform", client_platform);
        params.put("client", client);
        params.put("version", version);
        params.put("product_id", product_id + "");
        params.put("scid", scid);
        params.put("hash", hash);

        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for(Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
}
