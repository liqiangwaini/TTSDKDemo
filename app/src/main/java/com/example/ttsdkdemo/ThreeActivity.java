package com.example.ttsdkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;

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

    String title = "";
    String rType = "";
    private boolean isResume = false;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_three);
        ButterKnife.bind(this);

        title = getIntent().getStringExtra("title");
        rType = getIntent().getStringExtra("rType");
        type = getIntent().getStringExtra("type");

        setTitle(title);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isResume) {
            if ("fm".equals(rType)) {
                requestFmListData(type);
            } else if ("music".equals(rType)) {

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
