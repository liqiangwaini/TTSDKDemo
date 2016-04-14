package com.example.ttsdkdemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.tingtingfm.ttsdk.callback.ListCategoryCallBack;
import com.tingtingfm.ttsdk.callback.ListFmCallBack;
import com.tingtingfm.ttsdk.entity.CategoryInfo;
import com.tingtingfm.ttsdk.entity.FmEntity;
import com.tingtingfm.ttsdk.helper.AsyncData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 广播、音乐、点播一级分类数据页面
 * Created by lqsir on 2016/4/14.
 */
public class SecondActivity extends AppCompatActivity {
    List<CategoryInfo> values;

    Context context;

    SecondAdapter adapter;
    @Bind(R.id.grid_second)
    GridView gridView;
    ProgressDialog dialog;
    String title = "";
    String type = "";
    private boolean isResume = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.act_second);
        ButterKnife.bind(this);

        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");

        setTitle(title);

        adapter = new SecondAdapter();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryInfo info = (CategoryInfo) parent.getAdapter().getItem(position);

                if ("fm".equals(type)) {
                    if (info.getSub_catelist() == 1) {
                            requestSecondData(info.getType());
                        } else {
                            requestFmListData(info.getType());
                    }
                } else if ("music".equals(type)) {
                    requestFmListData(info.getType());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isResume) {
            isResume = true;

            if ("fm".equals(type)) {
                requestCategory();
            } else if ("music".equals(type)) {
                requestMusicCategory();
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
            public void onFail(String errorMessage) {

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
//                System.out.println(response.toString());
                showContent(response.toString());
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
//                System.out.println(response.toString());
                showContent(response.toString());
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
            public void onFail(String errorMessage) {

            }

            @Override
            public void onCancel() {
                dimiss();
            }
        });
    }

    private void dimiss() {
        dialog.dismiss();
    }

    private void show() {
        dialog = ProgressDialog.show(this, null, null);
    }

    private void showContent(String msg) {
        new AlertDialog.Builder(this).setMessage(msg).setPositiveButton("ok", null).show();
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
