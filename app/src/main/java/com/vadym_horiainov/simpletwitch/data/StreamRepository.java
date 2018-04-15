package com.vadym_horiainov.simpletwitch.data;

import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.models.LiveStreamsModel;
import com.vadym_horiainov.simpletwitch.models.Stream;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StreamRepository {

    private final StreamApi streamApi;

    public StreamRepository(StreamApi streamApi) {
        this.streamApi = streamApi;
    }

    public Observable<List<Stream>> getLiveStreams() {
        return streamApi.getLiveStreamsModel("0s4cg0hmn8rq4rrv4ex8rtkexoape7", 10)
                .subscribeOn(Schedulers.io())
                .doOnNext(liveStreamsModel -> {
                })
                .map(LiveStreamsModel::getStreams);
    }

    public Observable<String> getAdmiral() {
        return streamApi.getChannelToken("0s4cg0hmn8rq4rrv4ex8rtkexoape7", "dotastarladder_en")
                .subscribeOn(Schedulers.io())
                .map(jsonObject -> {
                    String token = jsonObject.get("token").getAsString();
                    String sig = jsonObject.get("sig").getAsString();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://usher.twitch.tv/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    StreamApi api = retrofit.create(StreamApi.class);
                    ResponseBody responseBody = api.getChannelPlaylist("dotastarladder_en", "twitchweb", sig, token,
                            "any", 1, true, true).execute().body();

                    Scanner scanner = new Scanner(new InputStreamReader(responseBody.byteStream()));
                    String result = "";
                    while (scanner.hasNextLine()) {
                        result += scanner.nextLine() + "\n";
                    }
                    return result;
                });
    }
}
