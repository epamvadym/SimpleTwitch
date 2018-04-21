package com.vadym_horiainov.simpletwitch.data;

import android.support.annotation.NonNull;

import com.vadym_horiainov.simpletwitch.BuildConfig;
import com.vadym_horiainov.simpletwitch.data.api.QueryParameters;
import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.models.LiveStreamsModel;
import com.vadym_horiainov.simpletwitch.models.Stream;
import com.vadym_horiainov.simpletwitch.models.StreamPlaylist;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class StreamRepository {
    private final StreamApi streamApi;
    private final StreamApi usherApi;

    public StreamRepository(StreamApi streamApi, StreamApi usherApi) {
        this.streamApi = streamApi;
        this.usherApi = usherApi;
    }

    public Observable<List<Stream>> getLiveStreams(final int limit, final int offset) {
        return streamApi.getLiveStreamsModel(BuildConfig.CLIENT_ID, limit, offset)
                .subscribeOn(Schedulers.io())
                .map(LiveStreamsModel::getStreams);
    }

    public Observable<StreamPlaylist> getStreamPlayList(final String channelName) {
        return streamApi.getChannelToken(BuildConfig.CLIENT_ID, channelName)
                .subscribeOn(Schedulers.io())
                .map(jsonObject -> {
                    QueryParameters parameters = new QueryParameters.Builder()
                            .add("player", "twitchweb")
                            .add("token", jsonObject.get("token").getAsString())
                            .add("sig", jsonObject.get("sig").getAsString())
                            .add("allow_audio_only", true)
                            .add("allow_source", true)
                            .add("type", "any")
                            .add("p", 1)
                            .build();

                    ResponseBody responseBody
                            = usherApi.getChannelPlaylist(channelName, parameters.getMap()).execute().body();

                    return StreamPlaylist.fromM3U8(getStringResponse(responseBody));
                });
    }

    @NonNull
    private String getStringResponse(ResponseBody responseBody) {
        Scanner scanner = new Scanner(new InputStreamReader(responseBody.byteStream()));
        StringBuilder result = new StringBuilder();
        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine()).append("\n");
        }
        responseBody.close();
        return result.toString();
    }
}
