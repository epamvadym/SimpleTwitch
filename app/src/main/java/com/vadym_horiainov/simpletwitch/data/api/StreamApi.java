package com.vadym_horiainov.simpletwitch.data.api;

import com.vadym_horiainov.simpletwitch.models.LiveStreamsModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface StreamApi {


    @GET("streams")
    Observable<LiveStreamsModel> getLiveStreams(@Header("Client-ID") String client);

}
