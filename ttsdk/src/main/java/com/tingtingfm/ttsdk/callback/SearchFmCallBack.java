package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.FmInfo;
import com.tingtingfm.ttsdk.entity.search.SearchFmEntity;
import com.tingtingfm.ttsdk.net.RequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class SearchFmCallBack implements RequestCallback<SearchFmEntity> {
    @Override
    public SearchFmEntity parseNetworkResponse(String content) {
        SearchFmEntity entity = new SearchFmEntity();

        try {
            JSONObject object = new JSONObject(content);

            entity.setIs_last(object.getInt("is_last"));
            entity.setTotal(object.getInt("total"));

            List<FmInfo> infos = new ArrayList<FmInfo>();

            JSONArray array = object.getJSONArray("fm_list");
            FmInfo info = null;
            for (int i = 0; i < array.length(); i++) {
                info = new FmInfo();
                info.setName(array.getJSONObject(i).getString("name"));
                info.setLive_url(array.getJSONObject(i).getString("live_url"));
                infos.add(info);
            }
            entity.setData(infos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entity;
    }
}
