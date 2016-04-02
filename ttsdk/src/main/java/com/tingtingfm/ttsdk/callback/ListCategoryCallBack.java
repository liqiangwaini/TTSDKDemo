package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.CategoryInfo;
import com.tingtingfm.ttsdk.net.RequestCallback;

import java.util.List;

/**
 * 分类列表callback
 * Created by lqsir on 2016/3/28.
 */
public abstract class ListCategoryCallBack implements RequestCallback<List<CategoryInfo>> {

    @Override
    public List<CategoryInfo> parseNetworkResponse(String content) {
        return null;
    }
}
