package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.VodInfo;
import com.tingtingfm.ttsdk.entity.search.SearchVodEntity;
import com.tingtingfm.ttsdk.net.RequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class SearchVodCallBack implements RequestCallback<SearchVodEntity> {
    @Override
    public SearchVodEntity parseNetworkResponse(String content) {
        SearchVodEntity entity = new SearchVodEntity();

        try {
            JSONObject object = new JSONObject(content);

            entity.setIs_last(object.getInt("is_last"));
            entity.setTotal(object.getInt("total"));

            List<VodInfo> infos = new ArrayList<VodInfo>();

            JSONArray array = object.getJSONArray("album_list");
            VodInfo info = null;
            for (int i = 0; i < array.length(); i++) {
                info = new VodInfo();
                info.setName(array.getJSONObject(i).getString("name"));
                info.setDuration(array.getJSONObject(i).getInt("duration"));
                info.setHls(array.getJSONObject(i).getString("hls"));
                info.setM4a(array.getJSONObject(i).getString("m4a"));
                info.setContentClassName(array.getJSONObject(i).getString("content_class_name"));
                infos.add(info);
            }
            entity.setData(infos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entity;
    }
}
