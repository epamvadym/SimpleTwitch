package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.vadym_horiainov.simpletwitch.BR;
import com.vadym_horiainov.simpletwitch.R;
import com.vadym_horiainov.simpletwitch.databinding.ActivityPlayStreamBinding;
import com.vadym_horiainov.simpletwitch.mvvm.base.BindingActivity;

import javax.inject.Inject;

public class PlayStreamActivity extends BindingActivity<ActivityPlayStreamBinding, PlayStreamActivityVM> {
    private static final String CHANNEL_NAME_EXTRA = "CHANNEL_NAME_EXTRA";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityPlayStreamBinding binding;

    private PlayerView playerView;
    private SimpleExoPlayer player;

    public static Intent getPlayStreamActivityIntent(Context packageContext, String channelName) {
        Intent intent = new Intent(packageContext, PlayStreamActivity.class);
        intent.putExtra(CHANNEL_NAME_EXTRA, channelName);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBinding();
        getViewModel().liveStreamOpened(getIntent().getStringExtra(CHANNEL_NAME_EXTRA));
        setUp();
        subscribeToLiveData();
    }

    private void setUp() {
        playerView = binding.playerView;
    }

    private void subscribeToLiveData() {
        getViewModel().getPlaylistUriLiveDataLiveData().observe(this, this::playVideo);
    }

    private void playVideo(Uri playlistUri) {

        SimpleExoPlayer player =
                ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());

        playerView.setPlayer(player);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)));


        MediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(playlistUri);

        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer() {


    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public PlayStreamActivityVM createViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(PlayStreamActivityVM.class);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_play_stream;
    }
}
