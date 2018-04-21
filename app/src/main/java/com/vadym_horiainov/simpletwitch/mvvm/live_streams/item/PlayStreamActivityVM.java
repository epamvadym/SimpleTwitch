package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.models.StreamPlaylist;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;
import com.vadym_horiainov.simpletwitch.util.Log;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import javax.inject.Inject;

public class PlayStreamActivityVM extends ActivityViewModel {
    private final MutableLiveData<String> videoUrlLiveData;
    private final StreamRepository streamRepository;
    private final SchedulerProvider schedulerProvider;
    private String channelName;

    @Inject
    PlayStreamActivityVM(@NonNull Application application, StreamRepository streamRepository,
                         SchedulerProvider schedulerProvider) {
        super(application);
        this.streamRepository = streamRepository;
        this.schedulerProvider = schedulerProvider;
        videoUrlLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getVideoUrlLiveData() {
        return videoUrlLiveData;
    }

    public void liveStreamOpened(@NonNull String channelName) {
        if (!channelName.equals(this.channelName)) {
            this.channelName = channelName;

            getCompositeDisposable().add(
                    streamRepository.getStreamPlayList(channelName)
                            .observeOn(schedulerProvider.ui())
                            .subscribe(
                                    streamPlaylist -> videoUrlLiveData.setValue(
                                            streamPlaylist.getStreamMap().get(StreamPlaylist.QUALITY_SOURCE)),
                                    throwable -> Log.e(TAG, "onItemClick: ", throwable)
                            )
            );
        }
    }
}
