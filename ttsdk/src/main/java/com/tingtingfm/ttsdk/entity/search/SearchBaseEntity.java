package com.tingtingfm.ttsdk.entity.search;

/**
 * Created by liqiang on 16/4/2.
 */
public class SearchBaseEntity {
    private int total;
    private int is_last;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIs_last() {
        return is_last;
    }

    public void setIs_last(int is_last) {
        this.is_last = is_last;
    }
}
