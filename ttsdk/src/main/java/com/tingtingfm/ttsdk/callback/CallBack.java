package com.tingtingfm.ttsdk.callback;

/**
 * Created by lqsir on 2016/3/28.
 */
public abstract class CallBack<T> {
    public void onStart() {

    }

    public abstract T parseNetworkResponse(String response) throws Exception;

    public abstract void onSuccess(T t);

    public abstract void onFail();
}
