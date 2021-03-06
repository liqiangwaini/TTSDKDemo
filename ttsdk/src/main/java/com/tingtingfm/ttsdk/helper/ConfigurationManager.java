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
    private String sCid = "";

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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void saveKey(String key, String secret) {
        this.key = key;
        this.secret = secret;

        if (application == null)
            return;

        SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("key", key);
        edit.putString("secret", secret);
        edit.commit();
    }

    public String getKey() {
        if (application == null)
            return secret;

        if (TextUtils.isEmpty(key)) {
            SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE,
                    Context.MODE_PRIVATE);
            key = preferences.getString("key", "");
        }

        return key;
    }

    public String getSecret() {
        if (application == null)
            return secret;

        if (TextUtils.isEmpty(secret)) {
            SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE,
                    Context.MODE_PRIVATE);
            secret = preferences.getString("secret", "");
        }

        return secret;
    }

    public boolean getStatusForTag(String tag) {
        SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE,
                Context.MODE_PRIVATE);
        return preferences.getBoolean(tag, false);
    }

    public void setStatus(String tag, boolean status) {
        SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE,
                Context.MODE_PRIVATE);
        preferences.edit().putBoolean(tag, status).commit();
    }


    public void saveScid(String scid) {
        this.sCid = scid;

        if (application == null)
            return;

        SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("scid", scid);
        edit.commit();
    }

    public String getScid() {
        if (application == null)
            return sCid;

        if (TextUtils.isEmpty(sCid)) {
            SharedPreferences preferences = application.getSharedPreferences(TTOPENFILE,
                    Context.MODE_PRIVATE);
            sCid = preferences.getString("scid", "");
        }

        return sCid;
    }
}
