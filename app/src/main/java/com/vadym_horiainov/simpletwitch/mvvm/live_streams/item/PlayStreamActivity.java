package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.vadym_horiainov.simpletwitch.BR;
import com.vadym_horiainov.simpletwitch.R;
import com.vadym_horiainov.simpletwitch.databinding.ActivityPlayStreamBinding;
import com.vadym_horiainov.simpletwitch.mvvm.base.BindingActivity;

public class PlayStreamActivity extends BindingActivity<ActivityPlayStreamBinding, PlayStreamActivityVM> {
    private static final String STREAM_URL_EXTRA = "STREAM_URL_EXTRA";
    private ActivityPlayStreamBinding binding;

    public static Intent getPlayStreamActivityIntent(Context packageContext, String streamUrl) {
        Intent intent = new Intent(packageContext, PlayStreamActivity.class);
        intent.putExtra(STREAM_URL_EXTRA, streamUrl);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBinding();

        String streamUrl = getIntent().getStringExtra(STREAM_URL_EXTRA);
        binding.vvStream.setVideoPath(streamUrl);
        binding.vvStream.start();
    }

    @Override
    public PlayStreamActivityVM createViewModel() {
        return new PlayStreamActivityVM(getApplication());
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
