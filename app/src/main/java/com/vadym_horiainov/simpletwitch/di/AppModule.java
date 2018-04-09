package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.TwitchApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private TwitchApplication appContext;

    public AppModule(TwitchApplication twitchApplication) {
        appContext = twitchApplication;
    }

    @Singleton
    @Provides
    TwitchApplication provideContext() {
        return appContext;
    }

}
