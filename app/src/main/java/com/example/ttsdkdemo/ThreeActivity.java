package com.example.ttsdkdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tingtingfm.ttsdk.callback.ListFmCallBack;
import com.tingtingfm.ttsdk.entity.FmEntity;
import com.tingtingfm.ttsdk.helper.AsyncData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liqiang on 16/4/14.
 */
public class ThreeActivity extends BaseActivity {
    @Bind(R.id.list_three)
    ListView listView;

    Context context;
    FmEntity fmEntity;

    String title = "";
    String rType = "";
    private boolean isResume = false;
    private String type;

    ThreeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                showContent(fmEntity.getFmList().get(position).toString());
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
            }
        }

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
//                showContent(response.toString());
                fmEntity = response;
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
                fmEntity = response;
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
            if (fmEntity == null)
                return 0;

            return fmEntity.getFmList().size();
        }

        @Override
        public Object getItem(int position) {
            if (fmEntity == null)
                return null;

            return fmEntity.getFmList().get(position);
        }

        @Override
        public long getItemId(int position) {
            if (fmEntity == null)
                return 0;

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (fmEntity == null)
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
            textView.setText(fmEntity.getFmList().get(position).getName());
            return convertView;
        }
    }
}
