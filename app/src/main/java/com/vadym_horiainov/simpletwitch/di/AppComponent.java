package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.TwitchApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(TwitchApplication twitchApplication);
}

