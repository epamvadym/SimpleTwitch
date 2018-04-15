package com.vadym_horiainov.simpletwitch.mvvm.live_streams;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.vadym_horiainov.simpletwitch.data.StreamRepository;
import com.vadym_horiainov.simpletwitch.models.Stream;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LiveStreamsActivityVM extends ActivityViewModel {

    private final ObservableList<LiveStreamsItemVM> liveStreamsItemViewModels;
    private final MutableLiveData<List<LiveStreamsItemVM>> liveStreamsItemLiveData;
    private final StreamRepository streamRepository;

    public LiveStreamsActivityVM(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
        liveStreamsItemViewModels = new ObservableArrayList<>();
        liveStreamsItemLiveData = new MutableLiveData<>();
        fetchData();
    }

    private void fetchData() {
        getCompositeDisposable().add(
                streamRepository.getLiveStreams()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(streams -> {
                                    liveStreamsItemLiveData.setValue(getViewModelList(streams));
                                },
                                throwable -> Log.e(TAG, "fetchData: ERROR", throwable))
        );
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
        List<LiveStreamsItemVM> liveStreamsItemViewModels = new ArrayList<>();
        for (Stream stream : streams) {
            liveStreamsItemViewModels.add(new LiveStreamsItemVM(
                    stream.getPreview().getMedium(), stream.getChannel().getLogo(),
                    stream.getChannel().getDisplayName(), stream.getChannel().getGame(),
                    stream.getChannel().getName()));
        }
        return liveStreamsItemViewModels;
    }
}