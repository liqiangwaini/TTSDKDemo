package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.CategoryInfo;
import com.tingtingfm.ttsdk.net.RequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类列表callback
 * Created by lqsir on 2016/3/28.
 */
public abstract class ListCategoryCallBack implements RequestCallback<List<CategoryInfo>> {

    @Override
    public List<CategoryInfo> parseNetworkResponse(String content) {
        List<CategoryInfo> values = new ArrayList<CategoryInfo>();

        try {
            JSONObject object = new JSONObject(content);

            JSONArray array = object.getJSONArray("category_list");

            CategoryInfo info = null;
            for (int i = 0; i < array.length(); i++) {
                info = new CategoryInfo();
                JSONObject item = (JSONObject) array.get(i);
                info.setName(item.getString("name"));
                if (item.has("sub_catelist")) {
                    info.setSub_catelist(item.getInt("sub_catelist"));
                }
                info.setType(item.getString("type"));
                values.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return values;
    }
}
