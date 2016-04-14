package com.example.ttsdkdemo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.tingtingfm.ttsdk.callback.ListCategoryCallBack;
import com.tingtingfm.ttsdk.callback.ListSelectFmCallBack;
import com.tingtingfm.ttsdk.entity.CategoryInfo;
import com.tingtingfm.ttsdk.entity.FmEntity;
import com.tingtingfm.ttsdk.helper.AsyncData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.tt_fm)
    TextView ttFm;
    @Bind(R.id.tt_music)
    TextView ttMusic;
    @Bind(R.id.tt_vod)
    TextView ttVod;
    @Bind(R.id.tt_search)
    TextView ttSearch;
    @Bind(R.id.tt_selection)
    TextView ttSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 读取广播第一级分类列表
     */
    @OnClick(R.id.tt_fm)
    public void clickFm() {
        AsyncData.showFmFirstCategory("firstcategory", new ListCategoryCallBack() {

            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<CategoryInfo> response) {
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

    @OnClick(R.id.tt_music)
    public void clickMusic() {
        AsyncData.showMusicFmCategory("musicCategory", new ListCategoryCallBack() {

            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<CategoryInfo> response) {
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

    @OnClick(R.id.tt_vod)
    public void clickVod() {
        AsyncData.showVodFirstCategory("vodCategory", new ListCategoryCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(List<CategoryInfo> response) {
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

    @OnClick(R.id.tt_search)
    public void clickSearch() {

    }

    @OnClick(R.id.tt_selection)
    public void clickSelection() {
        AsyncData.showSelectFm("selectFm", 1, new ListSelectFmCallBack() {
            @Override
            public void onStart() {
                show();
            }

            @Override
            public void onSuccess(FmEntity response) {

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

    private void show() {
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
    }

    private void dimiss() {
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }

    private void showContent(String msg) {
        new AlertDialog.Builder(this).setMessage(msg).setPositiveButton("ok", null).show();
    }
}
