package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.di.qualifires.ApiUrl;
import com.vadym_horiainov.simpletwitch.di.qualifires.UsherUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    StreamRepository provideStreamRepository(@ApiUrl StreamApi streamApi, @UsherUrl StreamApi usherApi) {
        return new StreamRepository(streamApi, usherApi);
    }
}
