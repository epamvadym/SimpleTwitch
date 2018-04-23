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
import com.google.android.exoplayer2.ui.DebugTextViewHelper;
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

    private SimpleExoPlayer player;
    private DebugTextViewHelper debugViewHelper;
    private DataSource.Factory dataSourceFactory;
    private MediaSource mediaSource;
    private DefaultTrackSelector trackSelector;

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
    }

    private void setUp() {
        trackSelector = new DefaultTrackSelector();
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        binding.playerView.setPlayer(player);
        dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)));
        debugViewHelper = new DebugTextViewHelper(player, binding.debugTextView);
    }


    private void subscribeToLiveData() {
        getViewModel().getPlaylistUriLiveDataLiveData().observe(this, this::playVideo);
    }

    private void playVideo(Uri playlistUri) {
        player.prepare(new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(playlistUri));
        player.setPlayWhenReady(true);
        debugViewHelper.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > Build.VERSION_CODES.M) {
            subscribeToLiveData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= Build.VERSION_CODES.M)) {
            subscribeToLiveData();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= Build.VERSION_CODES.M) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > Build.VERSION_CODES.M) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
            debugViewHelper.stop();
            debugViewHelper = null;
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
