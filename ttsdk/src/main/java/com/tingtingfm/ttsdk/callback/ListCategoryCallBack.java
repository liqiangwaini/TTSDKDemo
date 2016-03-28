package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.CategoryInfo;

import java.util.List;

/**
 * 分类列表callback
 * Created by lqsir on 2016/3/28.
 */
public abstract class ListCategoryCallBack extends CallBack<List<CategoryInfo>> {

    @Override
    public List<CategoryInfo> parseNetworkResponse(String response) throws Exception {
        return null;
    }
}
