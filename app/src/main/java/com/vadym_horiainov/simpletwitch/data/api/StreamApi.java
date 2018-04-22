package com.vadym_horiainov.simpletwitch.data.api;

import com.google.gson.JsonObject;
import com.vadym_horiainov.simpletwitch.models.LiveStreamsModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StreamApi {

    @GET("kraken/streams")
    Single<LiveStreamsModel> getLiveStreamsModel(@Header("Client-ID") String client,
                                                 @Query("limit") int limit,
                                                 @Query("offset") int offset);

    @GET("api/channels/{channel}/access_token")
    Single<JsonObject> getChannelToken(@Header("Client-ID") String client, @Path("channel") String channel);

}
