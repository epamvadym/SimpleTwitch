package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.vadym_horiainov.simpletwitch.R;
import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;
import com.vadym_horiainov.simpletwitch.util.Log;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import javax.inject.Inject;

public class PlayStreamActivityVM extends ActivityViewModel {
    private final MutableLiveData<SimpleExoPlayer> playerLiveData;
    private final StreamRepository streamRepository;
    private final SchedulerProvider schedulerProvider;
    private String channelName;
    private SimpleExoPlayer player;
    private DataSource.Factory dataSourceFactory;

    @Inject
    PlayStreamActivityVM(@NonNull Application application, StreamRepository streamRepository,
                         SchedulerProvider schedulerProvider) {
        super(application);
        this.streamRepository = streamRepository;
        this.schedulerProvider = schedulerProvider;
        playerLiveData = new MutableLiveData<>();
        initPlayer();
    }

    private void initPlayer() {
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        player = ExoPlayerFactory.newSimpleInstance(getApplication().getBaseContext(), trackSelector);
        playerLiveData.setValue(player);
        dataSourceFactory = new DefaultDataSourceFactory(getApplication(),
                Util.getUserAgent(getApplication(), getApplication().getString(R.string.app_name)));
    }

    public MutableLiveData<SimpleExoPlayer> getPlayerLiveData() {
        return playerLiveData;
    }

    public void liveStreamOpened(@NonNull String channelName) {
        if (!channelName.equals(this.channelName)) {
            this.channelName = channelName;

            getCompositeDisposable().add(
                    streamRepository.getStreamPlayList(channelName)
                            .observeOn(schedulerProvider.ui())
                            .subscribe(this::preparePlayer,
                                    throwable -> Log.e(TAG, "onItemClick: ", throwable))
            );
        }
    }

    private void preparePlayer(Uri playListUri) {
        player.prepare(new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(playListUri));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        player.setPlayWhenReady(hasFocus);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        player.release();
        player = null;
    }
}
