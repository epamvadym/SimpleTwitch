package com.vadym_horiainov.simpletwitch.mvvm.live_streams.list;

import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class LiveStreamsActivityModule {

    @Provides
    LinearLayoutManager provideLinearLayoutManager(LiveStreamsActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    LiveStreamsAdapter provideLiveStreamsAdapter() {
        return new LiveStreamsAdapter();
    }

}
