package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;
import com.vadym_horiainov.simpletwitch.util.Log;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import javax.inject.Inject;

public class PlayStreamActivityVM extends ActivityViewModel {
    private final MutableLiveData<Uri> playlistUriLiveData;
    private final StreamRepository streamRepository;
    private final SchedulerProvider schedulerProvider;
    private String channelName;

    @Inject
    PlayStreamActivityVM(@NonNull Application application, StreamRepository streamRepository,
                         SchedulerProvider schedulerProvider) {
        super(application);
        this.streamRepository = streamRepository;
        this.schedulerProvider = schedulerProvider;
        playlistUriLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Uri> getPlaylistUriLiveDataLiveData() {
        return playlistUriLiveData;
    }

    public void liveStreamOpened(@NonNull String channelName) {
        if (!channelName.equals(this.channelName)) {
            this.channelName = channelName;

            getCompositeDisposable().add(
                    streamRepository.getStreamPlayList(channelName)
                            .observeOn(schedulerProvider.ui())
                            .subscribe(playlistUriLiveData::setValue,
                                    throwable -> Log.e(TAG, "onItemClick: ", throwable)
                            )
            );
        }
    }
}
