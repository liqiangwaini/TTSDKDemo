package com.tingtingfm.ttsdk.net;

public interface RequestCallback<T> {
    public void onStart();

    public void onSuccess(T response);

    public void onFail(String errorMessage);

    public T parseNetworkResponse(String content);

    public void onCancel();
}
