package com.tingtingfm.ttsdk.entity;

/**
 * 频率信息
 * Created by lqsir on 2016/3/28.
 */
public class FmInfo {

    private String name;
    private String live_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLive_url() {
        return live_url;
    }

    public void setLive_url(String live_url) {
        this.live_url = live_url;
    }

    @Override
    public String toString() {
        return "FmInfo{" +
                "name='" + name + '\'' +
                ", live_url='" + live_url + '\'' +
                '}';
    }
}
