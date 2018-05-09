package com.vadym_horiainov.simpletwitch.data;

import android.net.Uri;

import com.vadym_horiainov.simpletwitch.BuildConfig;
import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.models.LiveStreamsModel;
import com.vadym_horiainov.simpletwitch.models.Stream;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class StreamRepository {
    private final StreamApi streamApi;
    private final SchedulerProvider schedulerProvider;

    @Inject
    StreamRepository(StreamApi streamApi, SchedulerProvider schedulerProvider) {
        this.streamApi = streamApi;
        this.schedulerProvider = schedulerProvider;
    }

    public Single<List<Stream>> getLiveStreams(final int limit, final int offset) {
        return streamApi.getLiveStreamsModel(BuildConfig.CLIENT_ID, limit, offset)
                .subscribeOn(schedulerProvider.io())
                .map(LiveStreamsModel::getStreams);
    }

    // https://www.johannesbader.ch/2014/01/find-video-url-of-twitch-tv-live-streams-or-past-broadcasts/
    // http://usher.twitch.tv/api/channel/hls/{channel}.m3u8? →
    // player=twitchweb&&token={token}&sig={sig}& →
    // allow_audio_only=true&allow_source=true&type=any&p={random}'
    public Single<Uri> getStreamPlayList(final String channelName) {
        return streamApi.getChannelToken(BuildConfig.CLIENT_ID, channelName)
                .subscribeOn(schedulerProvider.io())
                .map(jsonObject -> new Uri.Builder()
                        .scheme("http")
                        .authority("usher.twitch.tv")
                        .appendPath("api")
                        .appendPath("channel")
                        .appendPath("hls")
                        .appendPath(channelName + ".m3u8")
                        .appendQueryParameter("player", "twitchweb")
                        .appendQueryParameter("token", jsonObject.get("token").getAsString())
                        .appendQueryParameter("sig", jsonObject.get("sig").getAsString())
                        .appendQueryParameter("allow_audio_only", "true")
                        .appendQueryParameter("allow_source", "true")
                        .appendQueryParameter("type", "any")
                        .appendQueryParameter("p", "1")
                        .build()
                );
    }
}
