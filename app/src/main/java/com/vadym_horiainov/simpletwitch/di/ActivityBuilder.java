package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.mvvm.live_streams.item.PlayStreamActivity;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.item.PlayStreamActivityModule;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.list.LiveStreamsActivity;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.list.LiveStreamsActivityModule;
import com.vadym_horiainov.simpletwitch.mvvm.login.LoginActivity;
import com.vadym_horiainov.simpletwitch.mvvm.login.LoginActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            LiveStreamsActivityModule.class})
    abstract LiveStreamsActivity bindLiveStreamsActivity();

    @ContributesAndroidInjector(modules = {
            PlayStreamActivityModule.class
    })
    abstract PlayStreamActivity bindPlayStreamActivity();

    @ContributesAndroidInjector(modules = {
            LoginActivityModule.class
    })
    abstract LoginActivity bindLoginActivity();
}
