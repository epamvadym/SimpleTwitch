package com.vadym_horiainov.simpletwitch.util;

import com.vadym_horiainov.simpletwitch.BuildConfig;

public final class Log {

    private Log() {
    }

    public static void i(final String tag, final String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag, message);
        }
    }

    public static void d(final String tag, final String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.d(tag, message);
        }
    }

    public static void e(final String tag, final String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(tag, message);
        }
    }

    public static void e(final String tag, final String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(tag, message, throwable);
        }
    }

    public static void v(final String tag, final String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.v(tag, message);
        }
    }

    public static void w(final String tag, final String message) {
        if (BuildConfig.DEBUG) {
            android.util.Log.w(tag, message);
        }
    }
}
