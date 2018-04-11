package com.vadym_horiainov.simpletwitch.mvvm.live_streams;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.mvvm.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class LiveStreamsActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(LiveStreamsActivityVM liveStreamsActivityVM) {
        return new ViewModelProviderFactory<>(liveStreamsActivityVM);
    }

    @Provides
    LiveStreamsActivityVM provideLiveStreamsActivityViewModel(StreamApi streamApi) {
        return new LiveStreamsActivityVM(streamApi);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(LiveStreamsActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    LiveStreamsAdapter provideLiveStreamsAdapter() {
        return new LiveStreamsAdapter();
    }

}
