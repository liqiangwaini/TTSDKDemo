package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.FmEntity;
import com.tingtingfm.ttsdk.net.RequestCallback;

import java.util.List;

/**
 * 读取广播，音乐某个分类下的频率列表回调
 * Created by liqiang on 16/4/2.
 */
public abstract class ListFmCallBack implements RequestCallback<List<FmEntity>> {

    @Override
    public List<FmEntity> parseNetworkResponse(String content) {
        return null;
    }
}
