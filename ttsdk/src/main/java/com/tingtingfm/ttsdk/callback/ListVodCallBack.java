package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.VodInfo;
import com.tingtingfm.ttsdk.net.RequestCallback;

import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class ListVodCallBack implements RequestCallback<List<VodInfo>> {
    @Override
    public List<VodInfo> parseNetworkResponse(String content) {
        return null;
    }
}
