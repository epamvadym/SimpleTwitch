package com.vadym_horiainov.simpletwitch.data.prefs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class PreferencesHelper {
    private static final String PREF_KEY_ACCESS_TOKEN =
            "com.vadym_horiainov.simpletwitch_PREF_KEY_ACCESS_TOKEN";

    private final SharedPreferences preferences;

    public PreferencesHelper(Application appContext, String prefFileName) {
        this.preferences = appContext.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    public void saveToken(String accessToken) {
        preferences.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Nullable
    public String getToken() {
        return preferences.getString(PREF_KEY_ACCESS_TOKEN, null);
    }
}
