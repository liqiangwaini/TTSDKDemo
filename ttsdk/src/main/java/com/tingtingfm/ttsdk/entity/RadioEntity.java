package com.tingtingfm.ttsdk.entity;

import java.util.List;

/**
 * Created by lqsir on 2016/3/28.
 */
public class RadioEntity {
    private List<RadioInfo> fm_list;
    private int total;

    public List<RadioInfo> getFmList() {
        return fm_list;
    }

    public void setFmList(List<RadioInfo> list) {
        this.fm_list = fm_list;
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
                "fm_list=" + fm_list.toString() +
                ", total=" + total +
                '}';
    }
}
