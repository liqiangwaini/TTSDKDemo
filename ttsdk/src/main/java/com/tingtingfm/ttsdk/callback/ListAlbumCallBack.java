package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.AlbumInfo;
import com.tingtingfm.ttsdk.net.RequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class ListAlbumCallBack implements RequestCallback<List<AlbumInfo>>{

    @Override
    public List<AlbumInfo> parseNetworkResponse(String content) {
        List<AlbumInfo> infos = new ArrayList<AlbumInfo>();

        try {
            JSONObject object = new JSONObject(content);

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
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return infos;
    }
}
