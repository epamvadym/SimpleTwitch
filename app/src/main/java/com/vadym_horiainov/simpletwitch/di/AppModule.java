package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.util.rx.AppSchedulerProvider;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Singleton
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
