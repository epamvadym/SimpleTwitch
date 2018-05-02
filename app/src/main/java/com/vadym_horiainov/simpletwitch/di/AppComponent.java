package com.vadym_horiainov.simpletwitch.di;

import android.app.Application;

import com.vadym_horiainov.simpletwitch.TwitchApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        NetworkModule.class,
        DbModule.class
})
public interface AppComponent {

    void inject(TwitchApplication twitchApplication);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

