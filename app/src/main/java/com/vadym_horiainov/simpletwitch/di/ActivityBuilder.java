package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.mvvm.live_streams.item.PlayStreamActivity;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.item.PlayStreamActivityModule;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.list.LiveStreamsActivity;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.list.LiveStreamsActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            LiveStreamsActivityModule.class})
    abstract LiveStreamsActivity bindLiveStreamsActivity();

    @ContributesAndroidInjector(modules = {
            PlayStreamActivityModule.class
    })
    abstract PlayStreamActivity bindPlayStreamActivity();
}
