package com.vadym_horiainov.simpletwitch.data.api;

import com.google.gson.JsonObject;
import com.vadym_horiainov.simpletwitch.di.qualifires.UsherUrl;
import com.vadym_horiainov.simpletwitch.models.LiveStreamsModel;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface StreamApi {

    @GET("kraken/streams")
    Observable<LiveStreamsModel> getLiveStreamsModel(@Header("Client-ID") String client,
                                                     @Query("limit") int limit);

    @GET("api/channels/{channel}/access_token")
    Observable<JsonObject> getChannelToken(@Header("Client-ID") String client, @Path("channel") String channel);

    @UsherUrl
//    @Streaming // is it needed?
    @GET("api/channel/hls/{channel}.m3u8")
    Call<ResponseBody> getChannelPlaylist(@Path("channel") String channel, @QueryMap Map<String, Object> params);
}
