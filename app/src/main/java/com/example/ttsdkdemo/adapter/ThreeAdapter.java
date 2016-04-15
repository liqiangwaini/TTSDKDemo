package com.example.ttsdkdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ttsdkdemo.R;
import com.tingtingfm.ttsdk.entity.VodInfo;

import java.util.List;

public class ThreeAdapter extends BaseAdapter {
    List<VodInfo> vodInfos;
    Context context;

    public ThreeAdapter(Context context) {
        this.context = context;
    }

    public void setVodInfos(List<VodInfo> vodInfos) {
        this.vodInfos = vodInfos;
    }

    @Override
    public int getCount() {
        if (vodInfos == null)
            return 0;

        return vodInfos.size();
    }

    @Override
    public Object getItem(int position) {
        if (vodInfos == null)
            return null;

        return vodInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (vodInfos == null)
            return 0;

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (vodInfos == null)
            return null;

        TextView textView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_name, null);
            textView = (TextView) convertView.findViewById(R.id.item_name);
            convertView.setTag(textView);
        } else {
            textView = (TextView) convertView.getTag();
        }

        textView.setBackgroundResource(R.drawable.bg);
        textView.setText(vodInfos.get(position).getName());
        return convertView;
    }
}