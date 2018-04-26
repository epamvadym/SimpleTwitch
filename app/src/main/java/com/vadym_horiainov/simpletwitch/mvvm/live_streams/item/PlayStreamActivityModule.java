package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.widget.ArrayAdapter;

import com.vadym_horiainov.simpletwitch.R;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayStreamActivityModule {

    @Provides
    ArrayAdapter<String> provideArrayAdapter(PlayStreamActivity activity) {
        return new ArrayAdapter<>(activity, R.layout.spinner_quality_item);
    }

}
