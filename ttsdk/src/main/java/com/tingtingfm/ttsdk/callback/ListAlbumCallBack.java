package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.AlbumInfo;
import com.tingtingfm.ttsdk.net.RequestCallback;

import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class ListAlbumCallBack implements RequestCallback<List<AlbumInfo>>{

    @Override
    public List<AlbumInfo> parseNetworkResponse(String content) {
        return null;
    }
}
