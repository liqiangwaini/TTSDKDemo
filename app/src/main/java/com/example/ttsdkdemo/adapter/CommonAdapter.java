package com.example.ttsdkdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ttsdkdemo.R;

import java.util.List;

/**
 * Created by lenovo on 2016/4/15.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    Context context;
    List<T> infos;

    public CommonAdapter(Context context) {
        this.context = context;
    }

    public void setInfos(List<T> infos) {
        this.infos = infos;
    }

    @Override
    public int getCount() {
        if (infos == null)
            return 0;

        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        if (infos == null)
            return null;

        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (infos == null)
            return 0;

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (infos == null)
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
        convert(textView, infos.get(position));
        return convertView;
    }

    public abstract void  convert(TextView textView, T t);
}
