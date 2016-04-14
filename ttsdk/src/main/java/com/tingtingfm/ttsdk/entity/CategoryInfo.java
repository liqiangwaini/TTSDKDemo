package com.tingtingfm.ttsdk.entity;

/**
 * 分类信息
 * Created by lqsir on 2016/3/28.
 */
public class CategoryInfo {
    private String name;
    private String type;
    private int sub_catelist;

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

    /**
     * 是否存在二级分类 1:存在 0:不存在
     * @return
     */
    public int getSub_catelist() {
        return sub_catelist;
    }

    public void setSub_catelist(int sub_catelist) {
        this.sub_catelist = sub_catelist;
    }

    @Override
    public String toString() {
        return "CategoryInfo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sub_catelist=" + sub_catelist +
                '}';
    }
}
