package com.example.ttsdkdemo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.tingtingfm.ttsdk.entity.RadioInfo;

public class SelectFmAdapter extends CommonAdapter<RadioInfo> {
    public SelectFmAdapter(Context context) {
        super(context);
    }

    @Override
    public void convert(TextView textView, RadioInfo radioInfo) {
        textView.setText(radioInfo.getName());
    }
}