package com.example.ttsdkdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ttsdkdemo.R;
import com.tingtingfm.ttsdk.entity.AlbumInfo;

import java.util.List;

public class AlbumAdapter extends BaseAdapter {
    Context context;

    List<AlbumInfo> albumInfos;

    public AlbumAdapter(Context context) {
        this.context = context;
    }

    public void setAlbumInfos(List<AlbumInfo> albumInfos) {
        this.albumInfos = albumInfos;
    }

    @Override
        public int getCount() {
            if (albumInfos == null)
                return 0;

            return albumInfos.size();
        }

        @Override
        public Object getItem(int position) {
            if (albumInfos == null)
                return null;

            return albumInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            if (albumInfos == null)
                return 0;

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (albumInfos == null)
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
            textView.setText(albumInfos.get(position).getName());
            return convertView;
        }
    }