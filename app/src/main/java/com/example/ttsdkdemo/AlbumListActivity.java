package com.example.ttsdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ttsdkdemo.adapter.AlbumAdapter;
import com.tingtingfm.ttsdk.callback.ListAlbumCallBack;
import com.tingtingfm.ttsdk.callback.SearchAlbumCallBack;
import com.tingtingfm.ttsdk.entity.AlbumInfo;
import com.tingtingfm.ttsdk.entity.search.SearchAlbumEntity;
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

    String title = "";
    String rType = "";
    AlbumAdapter adapter;
    private boolean isResume = false;
    private String type;
    private String keywords;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_three);
        ButterKnife.bind(this);

        title = getIntent().getStringExtra("title");
        rType = getIntent().getStringExtra("rtype");
        type = getIntent().getStringExtra("type");
        keywords = getIntent().getStringExtra("keywords");

        setTitle(title);
        adapter = new AlbumAdapter(this);

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
                requestSearchAlbumListData(keywords, 1);
            }
        }
    }

    private void requestAlbumListData(String type) {
        AsyncData.showAlbumListForType("", type, new ListAlbumCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<AlbumInfo> response) {
                adapter.setAlbumInfos(response);
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

    public void requestSearchAlbumListData(String keywords, int page) {
        AsyncData.showSearchAlbum("", keywords, page, new SearchAlbumCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(SearchAlbumEntity response) {
                adapter.setAlbumInfos(response.getData());
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
}
