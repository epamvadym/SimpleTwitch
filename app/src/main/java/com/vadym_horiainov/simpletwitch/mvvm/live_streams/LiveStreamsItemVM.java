package com.vadym_horiainov.simpletwitch.mvvm.live_streams;

import android.databinding.ObservableField;

public class LiveStreamsItemVM {

    public final ObservableField<String> videoBannerUrl;

    public final ObservableField<String> logoUrl;

    public final ObservableField<String> displayName;

    public final ObservableField<String> game;


    public LiveStreamsItemVM(ObservableField<String> videoBannerUrl, ObservableField<String> logoUrl,
                             ObservableField<String> displayName, ObservableField<String> game) {
        this.videoBannerUrl = videoBannerUrl;
        this.logoUrl = logoUrl;
        this.displayName = displayName;
        this.game = game;
    }
}
