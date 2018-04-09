package com.vadym_horiainov.simpletwitch.data;

import com.vadym_horiainov.simpletwitch.data.api.StreamApi;

public class StreamRepository {

    private final StreamApi streamApi;

    public StreamRepository(StreamApi streamApi) {
        this.streamApi = streamApi;
    }
}
