package com.example.ttsdkdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liqiang on 16/4/14.
 */
public class BaseActivity extends AppCompatActivity {
    ProgressDialog dialog;

    protected void dimiss() {
        dialog.dismiss();
    }

    protected void show() {
        dialog = ProgressDialog.show(this, null, null);
    }
}
