package com.vadym_horiainov.simpletwitch.mvvm.live_streams.list;

import android.databinding.ObservableField;

import com.vadym_horiainov.simpletwitch.models.Stream;

public class LiveStreamsItemVM {
    public final ObservableField<String> streamPreviewUrl = new ObservableField<>();
    public final ObservableField<String> logoUrl = new ObservableField<>();
    public final ObservableField<String> displayName = new ObservableField<>();
    public final ObservableField<String> game = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> status = new ObservableField<>();
    private final LiveStreamsItemViewModelListener listener;

    public LiveStreamsItemVM(Stream stream, LiveStreamsItemViewModelListener listener) {
        this.streamPreviewUrl.set(stream.getPreview().getMedium());
        this.logoUrl.set(stream.getChannel().getLogo());
        this.displayName.set(stream.getChannel().getDisplayName());
        this.game.set(stream.getChannel().getGame());
        this.name.set(stream.getChannel().getName());
        this.status.set(stream.getChannel().getStatus());
        this.listener = listener;
    }

    public String getName() {
        return name.get();
    }

    public void onItemClick() {
        listener.onItemClick(name.get());
    }

    public interface LiveStreamsItemViewModelListener {
        void onItemClick(String channelName);
    }
}
