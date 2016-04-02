package com.tingtingfm.ttsdk.entity.search;

import com.tingtingfm.ttsdk.entity.FmInfo;

import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public class SearchFmEntity extends SearchBaseEntity {
    private List<FmInfo> data;

    public List<FmInfo> getData() {
        return data;
    }

    public void setData(List<FmInfo> data) {
        this.data = data;
    }
}
