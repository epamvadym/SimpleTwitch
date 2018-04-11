package com.vadym_horiainov.simpletwitch.mvvm.live_streams;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.models.Stream;
import com.vadym_horiainov.simpletwitch.mvvm.base.activities.ActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LiveStreamsActivityVM extends ActivityViewModel<LiveStreamsActivity> {

    private final ObservableList<LiveStreamsItemVM> liveStreamsItemViewModels;
    private final MutableLiveData<List<LiveStreamsItemVM>> liveStreamsItemLiveData;
    private final StreamApi streamApi;

    public LiveStreamsActivityVM(StreamApi streamApi) {
        this.streamApi = streamApi;
        liveStreamsItemViewModels = new ObservableArrayList<>();
        liveStreamsItemLiveData = new MutableLiveData<>();
        fetchData();
    }

    @SuppressLint("CheckResult")
    private void fetchData() {
        streamApi.getLiveStreams("0s4cg0hmn8rq4rrv4ex8rtkexoape7")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveStreamsModel -> {
                    if (liveStreamsModel != null && liveStreamsModel.getStreams() != null) {
                        liveStreamsItemLiveData.setValue(getViewModelList(liveStreamsModel.getStreams()));
                    }
                },
                        throwable -> Log.e(TAG, "fetchData: ERROR", throwable));
    }

    public ObservableList<LiveStreamsItemVM> getLiveStreamsItemViewModels() {
        return liveStreamsItemViewModels;
    }

    public MutableLiveData<List<LiveStreamsItemVM>> getLiveStreamsItemLiveData() {
        return liveStreamsItemLiveData;
    }

    public void addLiveStreamItemsToList(List<LiveStreamsItemVM> liveStreamsItems) {
        liveStreamsItemViewModels.clear();
        liveStreamsItemViewModels.addAll(liveStreamsItems);
    }

    public List<LiveStreamsItemVM> getViewModelList(List<Stream> streams) {
        List<LiveStreamsItemVM> liveStreamsItemVMList = new ArrayList<>();
        for (Stream stream : streams) {
            liveStreamsItemViewModels.add(new LiveStreamsItemVM(
                    stream.getChannel().getVideoBanner(), stream.getChannel().getLogo(),
                    stream.getChannel().getDisplayName(), stream.getChannel().getGame()));
        }
        return liveStreamsItemVMList;
    }
}