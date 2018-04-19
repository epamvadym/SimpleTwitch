package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;

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

    private VideoView vvStream;

    public static Intent getPlayStreamActivityIntent(Context packageContext, String streamUrl) {
        Intent intent = new Intent(packageContext, PlayStreamActivity.class);
        intent.putExtra(CHANNEL_NAME_EXTRA, streamUrl);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBinding();
        getViewModel().playStream(getIntent().getStringExtra(CHANNEL_NAME_EXTRA));
        setUp();
        subscribeToLiveData();
    }

    private void setUp() {
        vvStream = binding.vvStream;
    }

    private void subscribeToLiveData() {
        getViewModel().getVideoUrlLiveData().observe(this,
                this::playVideo);
    }

    private void playVideo(String videoPath) {
        if (!vvStream.isPlaying()) {
            vvStream.setVideoPath(videoPath);
            vvStream.start();
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
