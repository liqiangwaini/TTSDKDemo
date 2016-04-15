package com.example.ttsdkdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ttsdkdemo.R;
import com.tingtingfm.ttsdk.entity.FmInfo;

import java.util.List;

public class FmAdapter extends BaseAdapter {
    Context context;
    List<FmInfo> fmInfos;

    public FmAdapter(Context context) {
        this.context = context;
    }

    public void setFmInfos(List<FmInfo> fmInfos) {
        this.fmInfos = fmInfos;
    }

    @Override
    public int getCount() {
        if (fmInfos == null)
            return 0;

        return fmInfos.size();
    }

    @Override
    public FmInfo getItem(int position) {
        if (fmInfos == null)
            return null;

        return fmInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (fmInfos == null)
            return 0;

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (fmInfos == null)
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
        textView.setText(fmInfos.get(position).getName());
        return convertView;
    }
}