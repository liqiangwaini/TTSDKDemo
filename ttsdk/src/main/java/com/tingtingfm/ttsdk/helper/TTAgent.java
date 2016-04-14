package com.tingtingfm.ttsdk.helper;

import android.app.Application;

/**
 * key：tingtingfm
 * secret：5Gv1QHKO3EEPQM4qFtvkB9JgfnsEgEvV
 * Created by liqiang on 16/4/2.
 */
public class TTAgent {

    public static void init(Application application, String key, String secret) {
        ConfigurationManager.getInstance().setApplication(application);
        ConfigurationManager.getInstance().saveKey(key, secret);
    }

    public static String getOpenKey() {
        return ConfigurationManager.getInstance().getKey();
    }

    public static String getOpenSecret() {
        return ConfigurationManager.getInstance().getSecret();
    }
}
