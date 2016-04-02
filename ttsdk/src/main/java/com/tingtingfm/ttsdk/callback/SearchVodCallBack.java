package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.search.SearchVodEntity;
import com.tingtingfm.ttsdk.net.RequestCallback;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class SearchVodCallBack implements RequestCallback<SearchVodEntity> {
    @Override
    public SearchVodEntity parseNetworkResponse(String content) {
        return null;
    }
}
