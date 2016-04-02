package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.search.SearchFmEntity;
import com.tingtingfm.ttsdk.net.RequestCallback;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class SearchFmCallBack implements RequestCallback<SearchFmEntity> {
    @Override
    public SearchFmEntity parseNetworkResponse(String content) {
        return null;
    }
}
