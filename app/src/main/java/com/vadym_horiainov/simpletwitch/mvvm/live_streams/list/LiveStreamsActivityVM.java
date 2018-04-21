package com.vadym_horiainov.simpletwitch.mvvm.live_streams.list;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.models.Stream;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.item.PlayStreamActivity;
import com.vadym_horiainov.simpletwitch.util.Log;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LiveStreamsActivityVM extends ActivityViewModel
        implements LiveStreamsItemVM.LiveStreamsItemViewModelListener {
    private static final int PAGE_LIMIT = 50;
    private static final int LOAD_THRESHOLD = 10;
    private final ObservableList<LiveStreamsItemVM> liveStreamsItemViewModels;
    private final MutableLiveData<List<LiveStreamsItemVM>> liveStreamsItemsLiveData;
    private final ObservableBoolean isLoading;
    private final StreamRepository streamRepository;
    private final SchedulerProvider schedulerProvider;
    private int offset;

    @Inject
    LiveStreamsActivityVM(Application appContext, StreamRepository streamRepository,
                          SchedulerProvider schedulerProvider) {
        super(appContext);
        this.streamRepository = streamRepository;
        this.schedulerProvider = schedulerProvider;
        liveStreamsItemViewModels = new ObservableArrayList<>();
        liveStreamsItemsLiveData = new MutableLiveData<>();
        isLoading = new ObservableBoolean();
        fetchData();
    }

    private void fetchData() {
        getCompositeDisposable().add(
                streamRepository.getLiveStreams(PAGE_LIMIT, offset)
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                                streams -> {
                                    liveStreamsItemsLiveData.setValue(getViewModelList(streams));
                                    offset += PAGE_LIMIT;
                                    isLoading.set(false);
                                },
                                throwable -> Log.e(TAG, "fetchData: ", throwable)
                        )
        );
    }

    public void onRefresh() {
        isLoading.set(true);
        getCompositeDisposable().clear();
        liveStreamsItemViewModels.clear();
        offset = 0;
        fetchData();
    }

    public ObservableList<LiveStreamsItemVM> getLiveStreamsItemViewModels() {
        return liveStreamsItemViewModels;
    }

    public ObservableBoolean isLoading() {
        return isLoading;
    }

    public MutableLiveData<List<LiveStreamsItemVM>> getLiveStreamsItemsLiveData() {
        return liveStreamsItemsLiveData;
    }

    public void addLiveStreamItemsToList(List<LiveStreamsItemVM> liveStreamsItems) {
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
        if (!isLoading.get() && lastVisibleItemPosition > offset - LOAD_THRESHOLD) {
            isLoading.set(true);
            fetchData();
        }
    }
}