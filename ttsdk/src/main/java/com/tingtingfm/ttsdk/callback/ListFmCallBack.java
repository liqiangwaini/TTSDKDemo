package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.FmEntity;
import com.tingtingfm.ttsdk.net.RequestCallback;

/**
 * 读取广播、音乐下的频率列表
 * Created by lqsir on 2016/4/14.
 */
public abstract class ListFmCallBack implements RequestCallback<FmEntity> {

    @Override
    public FmEntity parseNetworkResponse(String content) {
        return null;
    }
}
