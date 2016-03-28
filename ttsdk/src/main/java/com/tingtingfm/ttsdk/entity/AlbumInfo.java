package com.tingtingfm.ttsdk.entity;

/**
 * 专辑信息
 * Created by lqsir on 2016/3/28.
 */
public class AlbumInfo {
    private String name;
    private String type;
    private String covers_url;
    private String recommendation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCovers_url() {
        return covers_url;
    }

    public void setCovers_url(String covers_url) {
        this.covers_url = covers_url;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
