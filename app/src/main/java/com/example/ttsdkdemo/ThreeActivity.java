package com.example.ttsdkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liqiang on 16/4/14.
 */
public class ThreeActivity extends AppCompatActivity {
    @Bind(R.id.list_three)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_three);
        ButterKnife.bind(this);
    }

    
}
