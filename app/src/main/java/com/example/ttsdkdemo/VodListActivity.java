package com.example.ttsdkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ttsdkdemo.adapter.VodAdapter;
import com.tingtingfm.ttsdk.callback.ListSelectVodCallBack;
import com.tingtingfm.ttsdk.callback.ListVodCallBack;
import com.tingtingfm.ttsdk.callback.SearchVodCallBack;
import com.tingtingfm.ttsdk.entity.VodInfo;
import com.tingtingfm.ttsdk.entity.search.SearchVodEntity;
import com.tingtingfm.ttsdk.helper.AsyncData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/4/15.
 */
public class VodListActivity extends BaseActivity {
    @Bind(R.id.list_three)
    ListView listView;

    String title = "";
    String rType = "";
    VodAdapter adapter;
    private boolean isResume = false;
    private String type;
    private String keyWords;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_three);
        ButterKnife.bind(this);

        title = getIntent().getStringExtra("title");
        rType = getIntent().getStringExtra("rtype");
        type = getIntent().getStringExtra("type");
        keyWords = getIntent().getStringExtra("keywords");

        setTitle(title);
        adapter = new VodAdapter(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VodInfo info = (VodInfo) parent.getAdapter().getItem(position);
                showContent(info.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isResume) {
            isResume = true;
            if ("vod".equals(rType) || "searchfm".equals(rType)) {
                requestVodListData(type);
            } else if ("search".equals(rType)) {
                requestSearchVodListData(keyWords, 1);
            } else if ("select".equals(rType)) {
                requestSelectVodListData(type, 1);
            }
        }
    }

    private void requestSearchVodListData(String keyWords, int page) {
        AsyncData.showSearchVod("", keyWords, page, new SearchVodCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(SearchVodEntity response) {
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

    private void requestVodListData(String type) {
        AsyncData.showVodListForType("", type, new ListVodCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<VodInfo> response) {
                adapter.setInfos(response);
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

    private void requestSelectVodListData(String type, int page) {
        AsyncData.showSelectFmVod("", type, page, new ListSelectVodCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<VodInfo> response) {
                adapter.setInfos(response);
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
