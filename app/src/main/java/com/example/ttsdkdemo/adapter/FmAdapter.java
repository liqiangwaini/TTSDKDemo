package com.example.ttsdkdemo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.tingtingfm.ttsdk.entity.FmInfo;

public class FmAdapter extends CommonAdapter<FmInfo> {

    public FmAdapter(Context context) {
        super(context);
    }

    @Override
    public void convert(TextView textView, FmInfo fmInfo) {
        textView.setText(fmInfo.getName());
    }
}