package com.tingtingfm.ttsdk.helper;

import com.tingtingfm.ttsdk.net.RequestManager;

/**
 * Created by liqiang on 16/4/2.
 */
class ConfigurationManager {
    private static volatile ConfigurationManager instance;
    private RequestManager manager;

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
}
