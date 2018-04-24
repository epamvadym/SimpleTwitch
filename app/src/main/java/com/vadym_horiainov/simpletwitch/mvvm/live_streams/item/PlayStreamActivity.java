package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.DebugTextViewHelper;
import com.vadym_horiainov.simpletwitch.BR;
import com.vadym_horiainov.simpletwitch.BuildConfig;
import com.vadym_horiainov.simpletwitch.R;
import com.vadym_horiainov.simpletwitch.databinding.ActivityPlayStreamBinding;
import com.vadym_horiainov.simpletwitch.mvvm.base.BindingActivity;

import javax.inject.Inject;

public class PlayStreamActivity extends BindingActivity<ActivityPlayStreamBinding, PlayStreamActivityVM> {
    private static final String CHANNEL_NAME_EXTRA = "CHANNEL_NAME_EXTRA";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityPlayStreamBinding binding;

    private DebugTextViewHelper debugViewHelper;

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
        getViewModel().onCreate(getIntent().getStringExtra(CHANNEL_NAME_EXTRA));
    }

    @Override
    public void onResume() {
        super.onResume();
        subscribeToLiveData();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG && debugViewHelper != null) {
            debugViewHelper.stop();
            debugViewHelper = null;
        }
    }

    private void subscribeToLiveData() {
        getViewModel().getPlayerLiveData().observe(this, this::playerViewSetPlayer);
    }

    private void playerViewSetPlayer(SimpleExoPlayer player) {
        binding.playerView.setPlayer(player);
        if (BuildConfig.DEBUG) {
            debugViewHelper = new DebugTextViewHelper(player, binding.debugTextView);
            debugViewHelper.start();
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
