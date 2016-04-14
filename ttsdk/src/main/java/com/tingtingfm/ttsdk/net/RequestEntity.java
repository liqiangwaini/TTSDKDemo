package com.tingtingfm.ttsdk.net;

import android.text.TextUtils;

import com.tingtingfm.ttsdk.Api;
import com.tingtingfm.ttsdk.helper.TTAgent;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 封装请求tag,url,请求参数
 * Created by liqiang on 16/4/2.
 */
public class RequestEntity {
    private static String ENCODING = "UTF-8";

    String tag;
    String url;

    List<RequestParameter> parameters = new ArrayList<RequestParameter>();

    public RequestEntity(String url) {
        this(null, url);
    }

    public RequestEntity(String tag, String url) {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not null");
        }

        this.url = url;
        this.tag = tag;

        //TODO 添加一些默认参数
        parameters.add(new RequestParameter("key", TTAgent.getOpenKey()));
    }

    public void addParams(String k, String v) {
        parameters.add(new RequestParameter(k, v));
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public List<RequestParameter> getParameters() {
        parameters.add(new RequestParameter("sign", generateSign()));
        return parameters;
    }

    private String generateSign() {
        String param1 = url.replace(Api.DOMAIN, "");
        String param2 = getParam2();
        String param3 =  TTAgent.getOpenSecret();

        String sign = param1 + "_" + param2 + "_" + param3;

        System.out.println(sign);

        return encryption(sign).toLowerCase();
    }

    /**
     * 拼接请求参数
     * @return 返回升序后拼接的参数
     */
    private String getParam2() {
        String result = "";
        try {
            Collections.sort(parameters, new ParamComparable());

            StringBuilder sb = new StringBuilder();
            for (Iterator<RequestParameter> iter = parameters.iterator(); iter.hasNext();) {
                RequestParameter p = (RequestParameter)iter.next();
                sb.append(URLEncoder.encode(p.getName(), ENCODING));
                sb.append("=");
                sb.append(URLEncoder.encode(p.getValue(), ENCODING));
                sb.append("&");
            }

            result = sb.toString().substring(0, sb.toString().length() - 1);
            result = dispatchStr(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 对字符做处理，URLEncoder.encode对*不做转义，对空格做+号转义，*需要替换成%2A, +号需要替换成%20
     * Uri.encode *需要替换成%2A
     *
     * @param value
     * @return
     */
    private String dispatchStr(String value) {
        return value.replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
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

}
