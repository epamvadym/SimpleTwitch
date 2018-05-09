package com.vadym_horiainov.simpletwitch.mvvm.live_streams.item;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.DebugTextViewHelper;
import com.vadym_horiainov.simpletwitch.BR;
import com.vadym_horiainov.simpletwitch.BuildConfig;
import com.vadym_horiainov.simpletwitch.R;
import com.vadym_horiainov.simpletwitch.databinding.ActivityPlayStreamBinding;
import com.vadym_horiainov.simpletwitch.mvvm.base.BindingActivity;

import java.util.List;

import javax.inject.Inject;

public class PlayStreamActivity extends BindingActivity<ActivityPlayStreamBinding, PlayStreamActivityVM> {
    private static final String EXTRA_CHANNEL_NAME = "EXTRA_CHANNEL_NAME";
    private static final String KEY_SPINNER_POSITION = "KEY_SPINNER_POSITION";

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    ArrayAdapter<String> arrayAdapter;

    private ActivityPlayStreamBinding binding;
    private DebugTextViewHelper debugViewHelper;
    private int qualitySpinnerPosition;

    public static Intent getPlayStreamActivityIntent(Context packageContext, String channelName) {
        Intent intent = new Intent(packageContext, PlayStreamActivity.class);
        intent.putExtra(EXTRA_CHANNEL_NAME, channelName);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            qualitySpinnerPosition = savedInstanceState.getInt(KEY_SPINNER_POSITION);
        }
        binding = getBinding();
        getViewModel().onCreate(getIntent().getStringExtra(EXTRA_CHANNEL_NAME));
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SPINNER_POSITION, binding.spinnerQuality.getSelectedItemPosition());
    }

    private void subscribeToLiveData() {
        getViewModel().getPlayerLiveData().observe(this, this::setUpPlayerView);
        getViewModel().getQualitiesLiveData().observe(this, this::setUpSpinner);
    }

    private void setUpPlayerView(SimpleExoPlayer player) {
        binding.playerView.setPlayer(player);
        if (BuildConfig.DEBUG) {
            debugViewHelper = new DebugTextViewHelper(player, binding.debugTextView);
            debugViewHelper.start();
        }
    }

    private void setUpSpinner(List<String> qualities) {
        arrayAdapter.addAll(qualities);
        binding.spinnerQuality.setAdapter(arrayAdapter);
        binding.spinnerQuality.setSelection(qualitySpinnerPosition);
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
