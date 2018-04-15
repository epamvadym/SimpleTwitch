package com.vadym_horiainov.simpletwitch.mvvm.live_streams;

import android.databinding.ObservableField;

public class LiveStreamsItemVM {

    public final ObservableField<String> streamPreviewUrl = new ObservableField<>();

    public final ObservableField<String> logoUrl = new ObservableField<>();

    public final ObservableField<String> displayName = new ObservableField<>();

    public final ObservableField<String> game = new ObservableField<>();

    public final ObservableField<String> name = new ObservableField<>();

    public LiveStreamsItemVM(String videoBannerUrl, String logoUrl,
                             String displayName, String game,
                             String name) {
        this.streamPreviewUrl.set(videoBannerUrl);
        this.logoUrl.set(logoUrl);
        this.displayName.set(displayName);
        this.game.set(game);
        this.name.set(name);
    }
}
