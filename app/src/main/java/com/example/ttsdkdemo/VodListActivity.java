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

import com.tingtingfm.ttsdk.callback.ListVodCallBack;
import com.tingtingfm.ttsdk.entity.VodInfo;
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

    Context context;
    List<VodInfo> vodInfos;

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
            if ("vod".equals(rType)) {
                requestVodListData(type);
            } else if ("search".equals(rType)) {
//                requestSearchAlbumListData(type);
            }
        }
    }

    private void requestVodListData(String type) {
        AsyncData.showVodListForType("", type, new ListVodCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<VodInfo> response) {
                vodInfos = response;
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
            if (vodInfos == null)
                return 0;

            return vodInfos.size();
        }

        @Override
        public Object getItem(int position) {
            if (vodInfos == null)
                return null;

            return vodInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            if (vodInfos == null)
                return 0;

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (vodInfos == null)
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
            textView.setText(vodInfos.get(position).getName());
            return convertView;
        }
    }


}
