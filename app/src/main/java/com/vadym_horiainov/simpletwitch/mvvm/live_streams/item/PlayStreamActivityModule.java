package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.mvvm.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayStreamActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(PlayStreamActivityVM playStreamActivityVM) {
        return new ViewModelProviderFactory<>(playStreamActivityVM);
    }

    @Provides
    PlayStreamActivityVM providePlayStreamActivityViewModel(Application appContext, StreamRepository streamRepository) {
        return new PlayStreamActivityVM(appContext, streamRepository);
    }

}
