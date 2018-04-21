package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.di.annotations.ApiUrl;
import com.vadym_horiainov.simpletwitch.di.annotations.UsherUrl;
import com.vadym_horiainov.simpletwitch.util.rx.AppSchedulerProvider;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Singleton
    @Provides
    StreamRepository provideStreamRepository(@ApiUrl StreamApi streamApi, @UsherUrl StreamApi usherApi,
                                             SchedulerProvider schedulerProvider) {
        return new StreamRepository(streamApi, usherApi, schedulerProvider);
    }

    @Singleton
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
