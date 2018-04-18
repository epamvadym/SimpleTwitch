package com.vadym_horiainov.simpletwitch.data;

import com.vadym_horiainov.simpletwitch.data.api.QueryParameters;
import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.models.LiveStreamsModel;
import com.vadym_horiainov.simpletwitch.models.Stream;

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

    public Observable<List<Stream>> getLiveStreams() {
        return streamApi.getLiveStreamsModel("0s4cg0hmn8rq4rrv4ex8rtkexoape7", 10)
                .subscribeOn(Schedulers.io())
                .doOnNext(liveStreamsModel -> {
                })
                .map(LiveStreamsModel::getStreams);
    }

    public Observable<String> getVideoUrl(final String channelName) {
        return streamApi.getChannelToken("0s4cg0hmn8rq4rrv4ex8rtkexoape7", channelName)
                .subscribeOn(Schedulers.io())
                .map(jsonObject -> {
                    String token = jsonObject.get("token").getAsString();
                    String sig = jsonObject.get("sig").getAsString();

                    QueryParameters parameters = new QueryParameters.Builder()
                            .add("player", "twitchweb")
                            .add("sig", jsonObject.get("token").getAsString())
                            .add("token", jsonObject.get("sig").getAsString())
                            .add("type", "any")
                            .add("p", 1)
                            .add("allow_audio_only", true)
                            .add("allow_source", true)
                            .build();

                    ResponseBody responseBody
                            = usherApi.getChannelPlaylist(channelName, parameters.getMap()).execute().body();

                    Scanner scanner = new Scanner(new InputStreamReader(responseBody.byteStream()));
                    String result = "";
                    while (scanner.hasNextLine()) {
                        result += scanner.nextLine() + "\n";
                    }
                    return result;
                });
    }
}
