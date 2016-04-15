package com.example.ttsdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ttsdkdemo.adapter.FmAdapter;
import com.example.ttsdkdemo.adapter.SelectFmAdapter;
import com.tingtingfm.ttsdk.callback.ListFmCallBack;
import com.tingtingfm.ttsdk.callback.ListSelectFmCallBack;
import com.tingtingfm.ttsdk.callback.SearchFmCallBack;
import com.tingtingfm.ttsdk.entity.FmEntity;
import com.tingtingfm.ttsdk.entity.FmInfo;
import com.tingtingfm.ttsdk.entity.RadioEntity;
import com.tingtingfm.ttsdk.entity.RadioInfo;
import com.tingtingfm.ttsdk.entity.search.SearchFmEntity;
import com.tingtingfm.ttsdk.helper.AsyncData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liqiang on 16/4/14.
 */
public class FmListActivity extends BaseActivity {
    @Bind(R.id.list_three)
    ListView listView;

    String title = "";
    String rType = "";
    private boolean isResume = false;
    private String type;

    FmAdapter adapter;
    SelectFmAdapter selectFmAdapter;
    private String keyWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_three);
        ButterKnife.bind(this);

        title = getIntent().getStringExtra("title");
        rType = getIntent().getStringExtra("rtype");
        type = getIntent().getStringExtra("type");
        keyWords = getIntent().getStringExtra("keywords");

        setTitle(title);

        adapter = new FmAdapter(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ("select".equals(rType)) {
                    RadioInfo radioInfo = (RadioInfo) parent.getAdapter().getItem(position);
                    Intent intent = new Intent(FmListActivity.this, VodListActivity.class);
                    intent.putExtra("rtype", rType);
                    intent.putExtra("type", radioInfo.getType());
                    intent.putExtra("title", "听听精选下音频列表 - " + radioInfo.getName());
                    startActivity(intent);
                } else {
                    FmInfo info = (FmInfo) parent.getAdapter().getItem(position);
                    showContent(info.toString());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isResume) {
            isResume = true;
            if ("fm".equals(rType)) {
                requestFmListData(type);
            } else if ("music".equals(rType)) {
                requestMusicListData(type);
            } else if ("search".equals(rType)) {
                requestSearchFmListData(keyWords, 1);
            } else if ("select".equals(rType)) {
                requestSelectFmListData(1);
            }
        }

    }

    private void requestSelectFmListData(int page) {
        AsyncData.showSelectFm("selectFm", page, new ListSelectFmCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(RadioEntity response) {
                selectFmAdapter = new SelectFmAdapter(FmListActivity.this);
                selectFmAdapter.setInfos(response.getFmList());
                listView.setAdapter(selectFmAdapter);
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

    private void requestSearchFmListData(String keyWords, int page) {
        AsyncData.showSearchFm("", keyWords, page, new SearchFmCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(SearchFmEntity response) {
                adapter.setInfos(response.getData());
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

    private void requestFmListData(String type) {
        AsyncData.showFmListForType("", type, 1, new ListFmCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(FmEntity response) {
                adapter.setInfos(response.getFmList());
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

    public void requestMusicListData(String type) {
        AsyncData.showMusicFmListForType("", type, 1, new ListFmCallBack() {

            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(FmEntity response) {
                adapter.setInfos(response.getFmList());
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
