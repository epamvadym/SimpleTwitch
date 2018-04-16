package com.vadym_horiainov.simpletwitch.mvvm.live_streams.list;

import android.databinding.ObservableField;

public class LiveStreamsItemVM {

    public final ObservableField<String> streamPreviewUrl = new ObservableField<>();

    public final ObservableField<String> logoUrl = new ObservableField<>();

    public final ObservableField<String> displayName = new ObservableField<>();

    public final ObservableField<String> game = new ObservableField<>();

    public final ObservableField<String> name = new ObservableField<>();

    public final LiveStreamsItemViewModelListener listener;

    public LiveStreamsItemVM(String videoBannerUrl, String logoUrl,
                             String displayName, String game,
                             String name, LiveStreamsItemViewModelListener listener) {
        this.streamPreviewUrl.set(videoBannerUrl);
        this.logoUrl.set(logoUrl);
        this.displayName.set(displayName);
        this.game.set(game);
        this.name.set(name);
        this.listener = listener;
    }

    public void onItemClick() {
        listener.onItemClick(name.get());
    }

    public interface LiveStreamsItemViewModelListener {
        void onItemClick(String channelName);
    }
}
