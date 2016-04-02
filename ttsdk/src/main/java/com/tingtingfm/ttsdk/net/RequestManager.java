package com.tingtingfm.ttsdk.net;

import java.util.ArrayList;
import java.util.List;

public class RequestManager {
    ArrayList<HttpRequest> requestList = null;

    public RequestManager() {
        // 异步请求列表
        requestList = new ArrayList<HttpRequest>();
    }

    /**
     * 添加Request到列表
     */
    void addRequest(final HttpRequest request) {
        requestList.add(request);
    }

    /**
     * 取消网络请求
     */
    public void cancelRequest(String tag) {
        if ((requestList != null) && (requestList.size() > 0)) {
            for (final HttpRequest request : requestList) {
                if (request.getConnection() != null) {
                    try {
                        request.getConnection().disconnect();
                        requestList.remove(request);
                    } catch (final UnsupportedOperationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 无参数调用
     */
    public HttpRequest createRequest(final String url,
                                     final RequestCallback requestCallback) {
        return createRequest(url, null, requestCallback);
    }

    /**
     * 有参数调用
     */
    public HttpRequest createRequest(final String url,
                                     final List<RequestParameter> params,
                                     final RequestCallback requestCallback) {
        final HttpRequest request = new HttpRequest(url, params,
                requestCallback);

        addRequest(request);
        return request;
    }
}
