package com.example.ttsdkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.tingtingfm.ttsdk.callback.ListCategoryCallBack;
import com.tingtingfm.ttsdk.entity.CategoryInfo;
import com.tingtingfm.ttsdk.helper.AsyncData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 广播、音乐、点播一级分类数据页面
 * Created by lqsir on 2016/4/14.
 */
public class SecondActivity extends BaseActivity {
    List<CategoryInfo> values;

    Context context;

    SecondAdapter adapter;
    @Bind(R.id.grid_second)
    GridView gridView;
    String title = "";
    String rType = "";
    String type = "";

    private boolean isResume = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.act_second);
        ButterKnife.bind(this);

        title = getIntent().getStringExtra("title");
        rType = getIntent().getStringExtra("rtype");
        type = getIntent().getStringExtra("type");

        setTitle(title);

        adapter = new SecondAdapter();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryInfo info = (CategoryInfo) parent.getAdapter().getItem(position);

                Intent intent = new Intent();
                intent.putExtra("rtype", rType);
                intent.putExtra("type", info.getType());
                if ("fm".equals(rType)) {
                    if (info.getSub_catelist() == 1) {
                        intent.setClass(SecondActivity.this, SecondActivity.class);
                        intent.putExtra("title", "听听广播二级分类列表");
                        startActivity(intent);
                    } else {
                        intent.setClass(SecondActivity.this, FmListActivity.class);
                        intent.putExtra("title", "听听广播频率列表 - " + info.getName());
                        startActivity(intent);
                    }
                } else if ("music".equals(rType)) {
                    intent.setClass(SecondActivity.this, FmListActivity.class);
                    intent.putExtra("title", "听听音乐频率列表 - " + info.getName());
                    startActivity(intent);
                } else if ("vod".equals(rType)) {
                    if (info.getSub_catelist() == 1) {
                        intent.setClass(SecondActivity.this, SecondActivity.class);
                        intent.putExtra("title", "听听音乐二级分类 - " + info.getName());
                        startActivity(intent);
                    } else {
                        intent.setClass(SecondActivity.this, AlbumListActivity.class);
                        intent.putExtra("title", "听听音乐专辑列表 - " + info.getName());
                        startActivity(intent);
                    }
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
                if (TextUtils.isEmpty(type)) {
                    requestCategory();
                } else {
                    requestSecondData(type);
                }
            } else if ("music".equals(rType)) {
                requestMusicCategory();
            } else if ("vod".equals(rType)) {
                if (TextUtils.isEmpty(type)) {
                    requestVodFirstCategory();
                } else {
                    requestVodSecondCategory(type);
                }
            }
        }
    }

    private void requestCategory() {
        AsyncData.showFmFirstCategory("firstcategory", new ListCategoryCallBack() {

            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<CategoryInfo> response) {
                values = response;
                gridView.setAdapter(adapter);
            }

            @Override
            public void onFail(int code, String errorMessage) {

            }

            @Override
            public void onCancel() {
                dimiss();
            }

        });
    }

    private void requestSecondData(String type) {
        AsyncData.showFmSecondCategory("", type, new ListCategoryCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<CategoryInfo> response) {
                values = response;
                gridView.setAdapter(adapter);
            }

            @Override
            public void onFail(int code, String errorMessage) {

            }

            @Override
            public void onCancel() {
                dimiss();
            }
        });
    }


    private void requestMusicCategory() {
        AsyncData.showMusicFmCategory("musicCategory", new ListCategoryCallBack() {

            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<CategoryInfo> response) {
                values = response;
                gridView.setAdapter(adapter);
            }

            @Override
            public void onFail(int code, String errorMessage) {

            }

            @Override
            public void onCancel() {
                dimiss();
            }
        });
    }

    private void requestVodFirstCategory() {
        AsyncData.showVodFirstCategory("vodCategory", new ListCategoryCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<CategoryInfo> response) {
                values = response;
                gridView.setAdapter(adapter);
            }

            @Override
            public void onFail(int code, String errorMessage) {

            }

            @Override
            public void onCancel() {
                dimiss();
            }
        });
    }

    private void requestVodSecondCategory(String type) {
        AsyncData.showVodSecondCategory("vodCategory2", type, new ListCategoryCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<CategoryInfo> response) {
                values = response;
                gridView.setAdapter(adapter);
            }

            @Override
            public void onFail(int code, String errorMessage) {

            }

            @Override
            public void onCancel() {
                dimiss();
            }
        });
    }

    class SecondAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (values == null)
                return 0;

            return values.size();
        }

        @Override
        public CategoryInfo getItem(int position) {
            if (values == null)
                return null;
            return values.get(position);
        }

        @Override
        public long getItemId(int position) {
            if (values == null)
                return 0;

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_name, null);
                textView = (TextView) convertView.findViewById(R.id.item_name);
                convertView.setTag(textView);
            } else {
                textView = (TextView) convertView.getTag();
            }

            CategoryInfo info = values.get(position);
            textView.setText(info.getName());
            if (info.getSub_catelist() == 1) {
                textView.setBackgroundResource(R.drawable.bg);
            } else {
                textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            }

            return convertView;
        }
    }
}
