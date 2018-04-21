package com.vadym_horiainov.simpletwitch.models;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamPlaylist {
    public static final String QUALITY_SOURCE = "chunked";
    public static final String QUALITY_AUTO = "auto";
    public static final String QUALITY_AUDIO_ONLY = "audio_only";
    public static final String QUALITY_720p60 = "720p60";
    public static final String QUALITY_720p30 = "720p30";
    public static final String QUALITY_480p30 = "480p30";
    public static final String QUALITY_360p30 = "360p30";
    public static final String QUALITY_144p30 = "144p30";

    private static final String[] QUALITIES = {
            QUALITY_AUTO,
            QUALITY_SOURCE,
            QUALITY_720p60,
            QUALITY_720p30,
            QUALITY_480p30,
            QUALITY_360p30,
            QUALITY_144p30,
            QUALITY_AUDIO_ONLY

    };

    private final Map<String, String> streamMap = new HashMap<>();

    public static StreamPlaylist fromM3U8(String m3u8) {
        StreamPlaylist playlist = new StreamPlaylist();
        playlist.streamMap.putAll(parseM3U8(m3u8));
        return playlist;
    }

    private static Map<String, String> parseM3U8(String m3u8) {
        Map<String, String> parsedMap = new HashMap<>();
        for (String quality : QUALITIES) {
            Pattern p = Pattern.compile("VIDEO=\"" + quality + "\"\\n(http:\\/\\/\\S+)");
            Matcher m = p.matcher(m3u8);

            if (m.find()) {
                parsedMap.put(quality, m.group(1));
            }
        }
        return parsedMap;
    }

    public Map<String, String> getStreamMap() {
        return streamMap;
    }
}
