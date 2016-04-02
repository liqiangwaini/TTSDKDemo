package com.tingtingfm.ttsdk.entity.search;

import com.tingtingfm.ttsdk.entity.VodInfo;

import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public class SearchVodEntity extends SearchBaseEntity{
    private List<VodInfo> data;

    public List<VodInfo> getData() {
        return data;
    }

    public void setData(List<VodInfo> data) {
        this.data = data;
    }
}
