package com.tingtingfm.ttsdk.helper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.tingtingfm.ttsdk.net.RequestManager;

/**
 * Created by liqiang on 16/4/2.
 */
class ConfigurationManager {
    private static String TTOPENFILE = "tt_sdk_file";
    private static volatile ConfigurationManager instance;
    private Application application;
    private RequestManager manager;
    private String key = "";
    private String secret = "";

    private ConfigurationManager() {
        manager = new RequestManager();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }

        return instance;
    }

    public RequestManager getManager() {
        return manager;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void saveKey(String key, String secret) {
        if (application == null)
            return;

        SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("key", key);
        edit.putString("secret", secret);
        edit.commit();
    }

    public String getKey() {
        if (application == null)
            return secret;

        if (TextUtils.isEmpty(key)) {
            SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE, Context.MODE_PRIVATE);
            key = preferences.getString("key", "");
        }

        return key;
    }

    public String getSecret() {
        if (application == null)
            return secret;

        if (TextUtils.isEmpty(secret) ) {
            SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE, Context.MODE_PRIVATE);
            secret = preferences.getString("secret", "");
        }

        return secret;
    }
}
