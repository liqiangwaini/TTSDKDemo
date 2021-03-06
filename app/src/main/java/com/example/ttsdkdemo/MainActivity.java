package com.example.ttsdkdemo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
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
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("title", "听听广播一级分类列表");
        intent.putExtra("rtype", "fm");
        startActivity(intent);
    }

    @OnClick(R.id.tt_music)
    public void clickMusic() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("title", "听听音乐分类列表");
        intent.putExtra("rtype", "music");
        startActivity(intent);
    }

    @OnClick(R.id.tt_vod)
    public void clickVod() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("title", "听听音乐一级分类");
        intent.putExtra("rtype", "vod");
        startActivity(intent);
    }

    @OnClick(R.id.tt_search)
    public void clickSearch() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra("title", "听听搜索");
        intent.putExtra("rtype", "search");
        startActivity(intent);
    }

    @OnClick(R.id.tt_selection)
    public void clickSelection() {
        Intent intent = new Intent(MainActivity.this, FmListActivity.class);
        intent.putExtra("title", "听听精选");
        intent.putExtra("rtype", "select");
        startActivity(intent);

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
