package com.tingtingfm.ttsdk.callback;

import com.tingtingfm.ttsdk.entity.RadioEntity;
import com.tingtingfm.ttsdk.entity.RadioInfo;
import com.tingtingfm.ttsdk.net.RequestCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取听听精选电台列表
 * Created by liqiang on 16/4/2.
 */
public abstract class ListSelectFmCallBack implements RequestCallback<RadioEntity> {

    @Override
    public RadioEntity parseNetworkResponse(String content) {
        RadioEntity radioEntity = new RadioEntity();

        try {
            JSONObject object = new JSONObject(content);

            radioEntity.setTotal(object.getInt("total"));
            JSONArray array = object.getJSONArray("fm_list");

            List<RadioInfo> values = new ArrayList<RadioInfo>();
            RadioInfo info = null;
            for (int i = 0; i < array.length(); i++) {
                info = new RadioInfo();
                JSONObject item = (JSONObject) array.get(i);
                info.setName(item.getString("name"));
                info.setType(item.getString("type"));
                values.add(info);
            }
            radioEntity.setFmList(values);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return radioEntity;
    }
}
