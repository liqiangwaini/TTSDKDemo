package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.search.SearchAlbumEntity;
import com.tingtingfm.ttsdk.net.RequestCallback;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class SearchAlbumCallBack implements RequestCallback<SearchAlbumEntity> {
    @Override
    public SearchAlbumEntity parseNetworkResponse(String content) {
        return null;
    }
}
