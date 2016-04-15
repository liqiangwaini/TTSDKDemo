package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.AlbumInfo;
import com.tingtingfm.ttsdk.entity.search.SearchAlbumEntity;
import com.tingtingfm.ttsdk.net.RequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class SearchAlbumCallBack implements RequestCallback<SearchAlbumEntity> {
    @Override
    public SearchAlbumEntity parseNetworkResponse(String content) {
        SearchAlbumEntity entity = new SearchAlbumEntity();

        try {
            JSONObject object = new JSONObject(content);

            entity.setIs_last(object.getInt("is_last"));
            entity.setTotal(object.getInt("total"));

            List<AlbumInfo> infos = new ArrayList<AlbumInfo>();

            JSONArray array = object.getJSONArray("album_list");
            AlbumInfo info = null;
            for (int i = 0; i < array.length(); i++) {
                info = new AlbumInfo();
                info.setName(array.getJSONObject(i).getString("name"));
                info.setType(array.getJSONObject(i).getString("type"));
                info.setCovers_url(array.getJSONObject(i).getString("covers_url"));
                info.setRecommendation(array.getJSONObject(i).getString("recommendation"));
                infos.add(info);
            }
            entity.setData(infos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entity;
    }
}
