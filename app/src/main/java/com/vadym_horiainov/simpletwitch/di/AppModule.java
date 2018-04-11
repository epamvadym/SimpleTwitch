package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.TwitchApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    TwitchApplication provideContext(TwitchApplication appContext) {
        return appContext;
    }

}
