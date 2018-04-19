package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.models.StreamPlaylist;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class PlayStreamActivityVM extends ActivityViewModel {
    private final MutableLiveData<String> videoUrlLiveData;
    private final StreamRepository streamRepository;
    private String channelName;

    public PlayStreamActivityVM(@NonNull Application application, StreamRepository streamRepository) {
        super(application);
        this.streamRepository = streamRepository;
        videoUrlLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getVideoUrlLiveData() {
        return videoUrlLiveData;
    }

    public void playStream(@NonNull String channelName) {
        if (!channelName.equals(this.channelName)) {
            this.channelName = channelName;

            getCompositeDisposable().add(
                    streamRepository.getStreamPlayList(channelName)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    streamPlaylist -> {
                                        videoUrlLiveData.setValue(streamPlaylist.getStreamMap().get(StreamPlaylist.QUALITY_SOURCE));
                                    },
                                    throwable -> Log.e(TAG, "onItemClick: ", throwable)
                            )
            );
        }
    }
}
