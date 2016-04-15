package com.example.ttsdkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tingtingfm.ttsdk.callback.ListAlbumCallBack;
import com.tingtingfm.ttsdk.entity.AlbumInfo;
import com.tingtingfm.ttsdk.helper.AsyncData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/4/15.
 */
public class AlbumListActivity extends BaseActivity {
    @Bind(R.id.list_three)
    ListView listView;

    Context context;
    List<AlbumInfo> albumInfos;

    String title = "";
    String rType = "";
    ThreeAdapter adapter;
    private boolean isResume = false;
    private String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_three);
        ButterKnife.bind(this);

        context = this;
        title = getIntent().getStringExtra("title");
        rType = getIntent().getStringExtra("rtype");
        type = getIntent().getStringExtra("type");

        setTitle(title);
        adapter = new ThreeAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlbumInfo info = (AlbumInfo) parent.getAdapter().getItem(position);

                Intent intent = new Intent();
                intent.putExtra("rtype", rType);
                intent.putExtra("type", info.getType());
                if ("vod".equals(rType)) {
                    intent.setClass(AlbumListActivity.this, VodListActivity.class);
                    intent.putExtra("title", "听听点播专辑下音频列表 - " + info.getName());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isResume) {
            isResume = true;
            if ("vod".equals(rType)) {
                requestAlbumListData(type);
            } else if ("search".equals(rType)) {
                requestSearchAlbumListData(type);
            }
        }
    }

    private void requestSearchAlbumListData(String type) {

    }

    private void requestAlbumListData(String type) {
        AsyncData.showAlbumListForType("", type, new ListAlbumCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<AlbumInfo> response) {
                albumInfos = response;
                listView.setAdapter(adapter);
            }

            @Override
            public void onFail(String errorMessage) {

            }

            @Override
            public void onCancel() {
                dimiss();
            }
        });
    }


    class ThreeAdapter extends BaseAdapter {

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
}
