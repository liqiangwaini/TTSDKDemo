package com.example.ttsdkdemo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.tingtingfm.ttsdk.entity.VodInfo;

public class VodAdapter extends CommonAdapter<VodInfo> {
    public VodAdapter(Context context) {
        super(context);
    }

    @Override
    public void convert(TextView textView, VodInfo vodInfo) {
        textView.setText(vodInfo.getName());
    }
}