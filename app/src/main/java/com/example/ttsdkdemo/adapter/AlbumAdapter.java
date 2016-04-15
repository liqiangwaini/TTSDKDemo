package com.example.ttsdkdemo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.tingtingfm.ttsdk.entity.AlbumInfo;

public class AlbumAdapter extends CommonAdapter<AlbumInfo> {

    public AlbumAdapter(Context context) {
        super(context);
    }

    @Override
    public void convert(TextView textView, AlbumInfo albumInfo) {
        textView.setText(albumInfo.getName());
    }
}