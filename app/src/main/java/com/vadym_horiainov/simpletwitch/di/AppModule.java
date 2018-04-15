package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.TwitchApplication;
import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.data.api.StreamApi;

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

    @Singleton
    @Provides
    StreamRepository provideStreamRepository(StreamApi streamApi) {
        return new StreamRepository(streamApi);
    }
}
