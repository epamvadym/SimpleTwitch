package com.vadym_horiainov.simpletwitch.mvvm;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vadym_horiainov.simpletwitch.BR;
import com.vadym_horiainov.simpletwitch.R;
import com.vadym_horiainov.simpletwitch.databinding.ActivityLiveStreamsBinding;
import com.vadym_horiainov.simpletwitch.mvvm.base.activities.BindingActivity;


public class LiveStreamsActivity extends BindingActivity<ActivityLiveStreamsBinding, LiveStreamsActivityVM> {

    private RecyclerView recyclerView;
    private LiveStreamsAdapter liveStreamsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = getBinding().rvLiveStreams;
        liveStreamsAdapter = new LiveStreamsAdapter();

        recyclerView.setAdapter(liveStreamsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getViewModel().streamList
                .observe(this, streams -> {
                    liveStreamsAdapter.setLiveStreamsItems(streams);
                    liveStreamsAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public LiveStreamsActivityVM onCreate() {
        return new LiveStreamsActivityVM(this);
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