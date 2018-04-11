package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.mvvm.live_streams.LiveStreamsActivity;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.LiveStreamsActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            LiveStreamsActivityModule.class,
            NetworkModule.class})
    abstract LiveStreamsActivity bindLiveStreamsActivity();

}
