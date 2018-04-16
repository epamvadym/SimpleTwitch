package com.vadym_horiainov.simpletwitch.mvvm.live_streams.list;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.models.Stream;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.item.PlayStreamActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LiveStreamsActivityVM extends ActivityViewModel implements LiveStreamsItemVM.LiveStreamsItemViewModelListener {

    private final ObservableList<LiveStreamsItemVM> liveStreamsItemViewModels;
    private final MutableLiveData<List<LiveStreamsItemVM>> liveStreamsItemsLiveData;
    private final MutableLiveData<String> chosenStreamNameLiveData;
    private final StreamRepository streamRepository;

    public LiveStreamsActivityVM(Application appContext, StreamRepository streamRepository) {
        super(appContext);
        this.streamRepository = streamRepository;
        liveStreamsItemViewModels = new ObservableArrayList<>();
        liveStreamsItemsLiveData = new MutableLiveData<>();
        chosenStreamNameLiveData = new MutableLiveData<>();
        fetchData();
    }

    private void fetchData() {
        getCompositeDisposable().add(
                streamRepository.getLiveStreams()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(streams -> {
                                    liveStreamsItemsLiveData.setValue(getViewModelList(streams));
                                },
                                throwable -> Log.e(TAG, "fetchData: ERROR", throwable))
        );
    }

    public ObservableList<LiveStreamsItemVM> getLiveStreamsItemViewModels() {
        return liveStreamsItemViewModels;
    }

    public MutableLiveData<List<LiveStreamsItemVM>> getLiveStreamsItemsLiveData() {
        return liveStreamsItemsLiveData;
    }

    public MutableLiveData<String> getChosenStreamNameLiveData() {
        return chosenStreamNameLiveData;
    }

    public void addLiveStreamItemsToList(List<LiveStreamsItemVM> liveStreamsItems) {
        liveStreamsItemViewModels.clear();
        liveStreamsItemViewModels.addAll(liveStreamsItems);
    }

    public List<LiveStreamsItemVM> getViewModelList(List<Stream> streams) {
        List<LiveStreamsItemVM> liveStreamsItemViewModels = new ArrayList<>();
        for (Stream stream : streams) {
            liveStreamsItemViewModels.add(new LiveStreamsItemVM(
                    stream.getPreview().getMedium(), stream.getChannel().getLogo(),
                    stream.getChannel().getDisplayName(), stream.getChannel().getGame(),
                    stream.getChannel().getName(), this));
        }
        return liveStreamsItemViewModels;
    }

    @Override
    public void onItemClick(String channelName) {
        getCompositeDisposable().add(
                streamRepository.getVideoUrl(channelName)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::openPlayStreamActivity,
                                throwable -> Log.e(TAG, "onItemClick: ", throwable))
        );
    }

    private void openPlayStreamActivity(String streamUrl) {
        Intent intent = PlayStreamActivity.getPlayStreamActivityIntent(getApplication(), streamUrl);
        getApplication().startActivity(intent);
    }
}