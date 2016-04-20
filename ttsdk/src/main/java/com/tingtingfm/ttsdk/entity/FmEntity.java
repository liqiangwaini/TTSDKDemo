package com.tingtingfm.ttsdk.entity;

import java.util.List;

/**
 * Created by lqsir on 2016/3/28.
 */
public class FmEntity {
    private List<FmInfo> fm_list;
    /**
     * 该分类总频率数
     */
    private int total;

    public List<FmInfo> getFmList() {
        return fm_list;
    }

    public void setFmList(List<FmInfo> list) {
        this.fm_list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "FmEntity{" +
                "fm_list=" +  fm_list.toString() +
                ", total=" + total +
                '}';
    }
}
