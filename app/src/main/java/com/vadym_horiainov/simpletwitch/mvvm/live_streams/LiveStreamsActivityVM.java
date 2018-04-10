package com.vadym_horiainov.simpletwitch.mvvm.live_streams;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.vadym_horiainov.simpletwitch.mvvm.base.activities.ActivityViewModel;

import java.util.List;

public class LiveStreamsActivityVM extends ActivityViewModel<LiveStreamsActivity> {

    private final ObservableList<LiveStreamsItemVM> liveStreamsItemViewModels = new ObservableArrayList<>();

    private final MutableLiveData<List<LiveStreamsItemVM>> liveStreamsItemLiveData = new MutableLiveData<>();

    public LiveStreamsActivityVM(LiveStreamsActivity activity) {
        super(activity);
    }

    public ObservableList<LiveStreamsItemVM> getLiveStreamsItemViewModels() {
        return liveStreamsItemViewModels;
    }
}