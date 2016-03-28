package com.example.ttsdkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tingtingfm.ttsdk.callback.ListCategoryCallBack;
import com.tingtingfm.ttsdk.entity.CategoryInfo;
import com.tingtingfm.ttsdk.helper.AsyncData;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AsyncData.showFmFirstCategory(new ListCategoryCallBack() {
            @Override
            public void onSuccess(List<CategoryInfo> categoryInfos) {

            }

            @Override
            public void onFail() {

            }
        });
    }
}
