package com.vadym_horiainov.simpletwitch.data.api;

import com.google.gson.JsonObject;
import com.vadym_horiainov.simpletwitch.models.LiveStreamsModel;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface StreamApi {

    @GET("kraken/streams")
    Observable<LiveStreamsModel> getLiveStreamsModel(@Header("Client-ID") String client,
                                                     @Query("limit") int limit);

    @GET("api/channels/{channel}/access_token")
    Observable<JsonObject> getChannelToken(@Header("Client-ID") String client, @Path("channel") String channel);

    @Streaming // is it needed?
    @GET("api/channel/hls/{channel}.m3u8")
    Call<ResponseBody> getChannelPlaylist(@Path("channel") String channel,
                                          @Query("player") String player,
                                          @Query("sig") String sig,
                                          @Query("token") String token,
                                          @Query("type") String type,
                                          @Query("p") int p,
                                          @Query("allow_audio_only") Boolean allowAudioOnly,
                                          @Query("allow_source") Boolean allowSource);
}
