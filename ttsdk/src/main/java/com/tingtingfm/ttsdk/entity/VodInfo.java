package com.tingtingfm.ttsdk.entity;

/**
 * 音频信息
 * Created by lqsir on 2016/3/28.
 */
public class VodInfo {
    private String name;
    private int duration;
    private String hls;
    private int m4a;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

    public int getM4a() {
        return m4a;
    }

    public void setM4a(int m4a) {
        this.m4a = m4a;
    }
}