package com.tingtingfm.ttsdk.entity;

import java.util.List;

/**
 * Created by lqsir on 2016/3/28.
 */
public class FmEntity {
    private List<FmInfo> fm_list;
    private int total;

    public List<FmInfo> getFmList() {
        return fm_list;
    }

    public void setFmList(List<FmInfo> list) {
        this.fm_list = fm_list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
