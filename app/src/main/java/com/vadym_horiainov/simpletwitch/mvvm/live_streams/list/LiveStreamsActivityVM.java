package com.vadym_horiainov.simpletwitch.mvvm.live_streams.list;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.models.Stream;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.item.PlayStreamActivity;
import com.vadym_horiainov.simpletwitch.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LiveStreamsActivityVM extends ActivityViewModel
        implements LiveStreamsItemVM.LiveStreamsItemViewModelListener {
    private static final int PAGE_LIMIT = 10;
    private static final int LOAD_THRESHOLD = 6;
    private final ObservableList<LiveStreamsItemVM> liveStreamsItemViewModels;
    private final MutableLiveData<List<LiveStreamsItemVM>> liveStreamsItemsLiveData;
    private final StreamRepository streamRepository;
    private int offset;
    private boolean isLoading;

    LiveStreamsActivityVM(Application appContext, StreamRepository streamRepository) {
        super(appContext);
        this.streamRepository = streamRepository;
        liveStreamsItemViewModels = new ObservableArrayList<>();
        liveStreamsItemsLiveData = new MutableLiveData<>();
        fetchData();
    }

    private void fetchData() {
        getCompositeDisposable().add(
                streamRepository.getLiveStreams(PAGE_LIMIT, offset)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                streams -> {
                                    liveStreamsItemsLiveData.setValue(getViewModelList(streams));
                                    offset += PAGE_LIMIT;
                                    isLoading = false;
                                    Log.d(TAG, "fetchData: offset - " + offset);
                                },
                                throwable -> Log.e(TAG, "fetchData: ERROR", throwable)
                        )
        );
    }

    public ObservableList<LiveStreamsItemVM> getLiveStreamsItemViewModels() {
        return liveStreamsItemViewModels;
    }

    public MutableLiveData<List<LiveStreamsItemVM>> getLiveStreamsItemsLiveData() {
        return liveStreamsItemsLiveData;
    }

    public void addLiveStreamItemsToList(List<LiveStreamsItemVM> liveStreamsItems) {
        liveStreamsItemViewModels.clear();
        liveStreamsItemViewModels.addAll(liveStreamsItems);
    }

    private List<LiveStreamsItemVM> getViewModelList(List<Stream> streams) {
        List<LiveStreamsItemVM> liveStreamsItemViewModels = new ArrayList<>();
        for (Stream stream : streams) {
            liveStreamsItemViewModels.add(new LiveStreamsItemVM(stream, this));
        }
        return liveStreamsItemViewModels;
    }

    @Override
    public void onItemClick(String channelName) {
        openPlayStreamActivity(channelName);
    }

    private void openPlayStreamActivity(String channelName) {
        Intent intent = PlayStreamActivity.getPlayStreamActivityIntent(getApplication(), channelName);
        getApplication().startActivity(intent);
    }

    public void listScrolled(int lastVisibleItemPosition) {
        if (!isLoading && lastVisibleItemPosition > offset - LOAD_THRESHOLD) {
            isLoading = true;
            fetchData();
        }
    }
}