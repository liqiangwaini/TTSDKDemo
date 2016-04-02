package com.tingtingfm.ttsdk.entity.search;

import com.tingtingfm.ttsdk.entity.AlbumInfo;

import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public class SearchAlbumEntity extends SearchBaseEntity {
    private List<AlbumInfo> data;

    public List<AlbumInfo> getData() {
        return data;
    }

    public void setData(List<AlbumInfo> data) {
        this.data = data;
    }
}
