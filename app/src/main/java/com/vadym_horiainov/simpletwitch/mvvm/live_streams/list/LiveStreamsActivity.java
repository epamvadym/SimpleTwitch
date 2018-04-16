package com.vadym_horiainov.simpletwitch.mvvm.live_streams.list;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.vadym_horiainov.simpletwitch.BR;
import com.vadym_horiainov.simpletwitch.R;
import com.vadym_horiainov.simpletwitch.databinding.ActivityLiveStreamsBinding;
import com.vadym_horiainov.simpletwitch.mvvm.base.BindingActivity;

import javax.inject.Inject;

public class LiveStreamsActivity extends BindingActivity<ActivityLiveStreamsBinding, LiveStreamsActivityVM> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    LiveStreamsAdapter liveStreamsAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    private ActivityLiveStreamsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBinding();
        setUp();
        subscribeToLiveData();
    }

    private void setUp() {
        binding.rvLiveStreams.setLayoutManager(linearLayoutManager);
        binding.rvLiveStreams.setAdapter(liveStreamsAdapter);
    }

    private void subscribeToLiveData() {
        getViewModel().getLiveStreamsItemsLiveData().observe(this,
                liveStreamsItemVMS -> getViewModel().addLiveStreamItemsToList(liveStreamsItemVMS));
    }

    @Override
    public LiveStreamsActivityVM createViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(LiveStreamsActivityVM.class);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_live_streams;
    }

}