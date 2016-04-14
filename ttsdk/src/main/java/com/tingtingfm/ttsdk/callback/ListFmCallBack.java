package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.FmEntity;
import com.tingtingfm.ttsdk.entity.FmInfo;
import com.tingtingfm.ttsdk.net.RequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取广播、音乐下的频率列表
 * Created by lqsir on 2016/4/14.
 */
public abstract class ListFmCallBack implements RequestCallback<FmEntity> {

    @Override
    public FmEntity parseNetworkResponse(String content) {
        FmEntity fmEntity = new FmEntity();

        try {
            JSONObject object = new JSONObject(content);

            fmEntity.setTotal(object.getInt("total"));
            JSONArray array = object.getJSONArray("fm_list");

            List<FmInfo> values = new ArrayList<FmInfo>();
            FmInfo info = null;
            for (int i = 0; i < array.length(); i++) {
                info = new FmInfo();
                JSONObject item = (JSONObject) array.get(i);
                info.setName(item.getString("name"));
                info.setLive_url(item.getString("live_url"));
                values.add(info);
            }
            fmEntity.setFmList(values);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fmEntity;
    }
}
