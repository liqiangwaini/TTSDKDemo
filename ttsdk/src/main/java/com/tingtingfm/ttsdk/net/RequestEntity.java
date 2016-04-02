package com.tingtingfm.ttsdk.net;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装请求tag,url,请求参数
 * Created by liqiang on 16/4/2.
 */
public class RequestEntity {
    String tag;
    String url;
    List<RequestParameter> parameters = new ArrayList<RequestParameter>();

    public RequestEntity(String url) {
        this(null, url);
    }

    public RequestEntity(String tag, String url) {
        this.url = url;
        this.tag = tag;

        //TODO 添加一些默认参数
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
        return parameters;
    }
}
