package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsManifest;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.FixedTrackSelection;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.vadym_horiainov.simpletwitch.R;
import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;
import com.vadym_horiainov.simpletwitch.util.Log;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class PlayStreamActivityVM extends ActivityViewModel {
    private final MutableLiveData<SimpleExoPlayer> playerLiveData;
    private final MutableLiveData<List<String>> qualitiesLiveData;
    private final ObservableBoolean isLoading;
    private final StreamRepository streamRepository;
    private final SchedulerProvider schedulerProvider;
    private String channelName;

    private SimpleExoPlayer player;
    private DataSource.Factory dataSourceFactory;
    private DefaultTrackSelector trackSelector;
    private boolean qualitiesAdded;

    private ExoPlayer.DefaultEventListener exoPlayerEventListener = new Player.DefaultEventListener() {
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            isLoading.set(playbackState != Player.STATE_READY);
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            // todo find out the optimal way how to get quality list
            HlsManifest manifest = (HlsManifest) player.getCurrentManifest();
            if (!qualitiesAdded && manifest != null) {
                Pattern pattern = Pattern.compile("NAME=\"(.+?)\"");
                List<String> qualities = new ArrayList<>();
                for (String tag : manifest.masterPlaylist.tags) {
                    Matcher matcher = pattern.matcher(tag);
                    if (matcher.find() && matcher.groupCount() == 1) {
                        qualities.add(matcher.group(1));
                    }
                }
                qualitiesLiveData.setValue(qualities);
                qualitiesAdded = true;
            }
        }
    };

    @Inject
    PlayStreamActivityVM(@NonNull Application application, StreamRepository streamRepository,
                         SchedulerProvider schedulerProvider) {
        super(application);
        this.streamRepository = streamRepository;
        this.schedulerProvider = schedulerProvider;
        playerLiveData = new MutableLiveData<>();
        qualitiesLiveData = new MutableLiveData<>();
        isLoading = new ObservableBoolean();
        initPlayer();
    }

    private void initPlayer() {
        trackSelector = new DefaultTrackSelector();
        player = ExoPlayerFactory.newSimpleInstance(getApplication().getBaseContext(), trackSelector);
        player.addListener(exoPlayerEventListener);
        playerLiveData.setValue(player);
        dataSourceFactory = new DefaultDataSourceFactory(getApplication(),
                Util.getUserAgent(getApplication(), getApplication().getString(R.string.app_name)));
    }

    public MutableLiveData<SimpleExoPlayer> getPlayerLiveData() {
        return playerLiveData;
    }

    public MutableLiveData<List<String>> getQualitiesLiveData() {
        return qualitiesLiveData;
    }

    public ObservableBoolean isLoading() {
        return isLoading;
    }

    public void onCreate(@NonNull String channelName) {
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
        HlsMediaSource hlsMediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(playListUri);
        player.prepare(hlsMediaSource);
    }

    public void qualitySelected(int position) {
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
        TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(0);
        MappingTrackSelector.SelectionOverride override = trackSelector.getSelectionOverride(0, trackGroups);

        override = new MappingTrackSelector.SelectionOverride(new FixedTrackSelection.Factory(), 0, position);
        trackSelector.setSelectionOverride(0, trackGroups, override);
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
