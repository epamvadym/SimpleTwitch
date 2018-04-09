package com.vadym_horiainov.simpletwitch.mvvm;


import android.arch.lifecycle.MutableLiveData;

import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.models.Stream;
import com.vadym_horiainov.simpletwitch.mvvm.base.activities.ActivityViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveStreamsActivityVM extends ActivityViewModel<LiveStreamsActivity> {

    StreamApi service;

    final MutableLiveData<List<Stream>> streamList = new MutableLiveData<>();

    public LiveStreamsActivityVM(LiveStreamsActivity activity) {
        super(activity);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://StreamApi.twitch.tv/kraken/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(StreamApi.class);

        Disposable disp = service.getLiveStreams("0s4cg0hmn8rq4rrv4ex8rtkexoape7")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveStreamModel -> {
                    streamList.postValue(liveStreamModel.getStreams());
                });
    }

}