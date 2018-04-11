package com.vadym_horiainov.simpletwitch.mvvm.live_streams;

import android.databinding.ObservableField;

public class LiveStreamsItemVM {

    public final ObservableField<String> videoBannerUrl = new ObservableField<>();

    public final ObservableField<String> logoUrl = new ObservableField<>();

    public final ObservableField<String> displayName = new ObservableField<>();

    public final ObservableField<String> game = new ObservableField<>();

    public LiveStreamsItemVM(String videoBannerUrl, String logoUrl,
                             String displayName, String game) {
        this.videoBannerUrl.set(videoBannerUrl);
        this.logoUrl.set(logoUrl);
        this.displayName.set(displayName);
        this.game.set(game);
    }
}
