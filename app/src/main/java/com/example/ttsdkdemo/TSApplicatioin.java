package com.example.ttsdkdemo;

import android.app.Application;

import com.tingtingfm.ttsdk.helper.TTAgent;

/**
 * Created by lqsir on 2016/4/14.
 */
public class TSApplicatioin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TTAgent.init(this, "tingtingfm", "5Gv1QHKO3EEPQM4qFtvkB9JgfnsEgEvV");
    }
}
