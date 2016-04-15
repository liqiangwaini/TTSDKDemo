package com.example.ttsdkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/4/15.
 */
public class SearchActivity extends BaseActivity {

    Context context;

    @Bind(R.id.search_editText)
    EditText searchEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search);
        ButterKnife.bind(this);

        context = this;

        setTitle("听听搜索");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.search_album)
    public void onclickSearchAlbum() {
        String keyWords = getKeywords();
        if (!TextUtils.isEmpty(keyWords)) {
            Intent intent = new Intent(SearchActivity.this, AlbumListActivity.class);
            intent.putExtra("title", "听听搜索-搜专辑-" + keyWords);
            intent.putExtra("rtype", "search");
            intent.putExtra("keywords", keyWords);
            startActivity(intent);
        }
    }

    @OnClick(R.id.search_vod)
    public void onclickSearchVod() {
        String keyWords = getKeywords();
        if (!TextUtils.isEmpty(keyWords)) {
            Intent intent = new Intent(SearchActivity.this, VodListActivity.class);
            intent.putExtra("title", "听听搜索-搜音频-" + keyWords);
            intent.putExtra("rtype", "search");
            intent.putExtra("keywords", keyWords);
            startActivity(intent);
        }
    }

    @OnClick(R.id.search_fm)
    public void onclickSearchFm() {
        String keyWords = getKeywords();
        if (!TextUtils.isEmpty(keyWords)) {
            Intent intent = new Intent(SearchActivity.this, FmListActivity.class);
            intent.putExtra("title", "听听搜索-搜频率-" + keyWords);
            intent.putExtra("rtype", "search");
            intent.putExtra("keywords", keyWords);
            startActivity(intent);
        }
    }

    private String getKeywords() {
        return searchEditText.getText().toString().trim();
    }
}
