package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.VodInfo;
import com.tingtingfm.ttsdk.net.RequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiang on 16/4/2.
 */
public abstract class ListVodCallBack implements RequestCallback<List<VodInfo>> {
    @Override
    public List<VodInfo> parseNetworkResponse(String content) {
        List<VodInfo> infos = new ArrayList<VodInfo>();

        try {
            JSONObject object = new JSONObject(content);

            JSONArray array = object.getJSONArray("album_audio_list");
            VodInfo info = null;
            for (int i = 0; i < array.length(); i++) {
                info = new VodInfo();
                info.setName(array.getJSONObject(i).getString("name"));
                info.setDuration(array.getJSONObject(i).getInt("duration"));
                info.setHls(array.getJSONObject(i).getString("hls"));
                info.setM4a(array.getJSONObject(i).getString("m4a"));
                infos.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return infos;
    }
}
