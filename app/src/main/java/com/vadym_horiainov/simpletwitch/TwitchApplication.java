package com.vadym_horiainov.simpletwitch;

import android.app.Application;

import com.vadym_horiainov.simpletwitch.di.AppComponent;
import com.vadym_horiainov.simpletwitch.di.AppModule;
import com.vadym_horiainov.simpletwitch.di.DaggerAppComponent;

public class TwitchApplication extends Application {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        buildAppComponent();
    }

    private void buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
