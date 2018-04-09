package com.vadym_horiainov.simpletwitch.models;

import com.google.gson.annotations.SerializedName;

public class Stream {

    @SerializedName("_id")
    private String id;

    @SerializedName("average_fps")
    private String averageFps;

    @SerializedName("channel")
    private Channel channel;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("delay")
    private Integer delay;

    @SerializedName("game")
    private String game;

    @SerializedName("is_playlist")
    private Boolean isPlaylist;

    @SerializedName("preview")
    private Preview preview;

    @SerializedName("video_height")
    private Integer videoHeight;

    @SerializedName("viewers")
    private Integer viewers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAverageFps() {
        return averageFps;
    }

    public void setAverageFps(String averageFps) {
        this.averageFps = averageFps;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Boolean getIsPlaylist() {
        return isPlaylist;
    }

    public void setIsPlaylist(Boolean isPlaylist) {
        this.isPlaylist = isPlaylist;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public Integer getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public Integer getViewers() {
        return viewers;
    }

    public void setViewers(Integer viewers) {
        this.viewers = viewers;
    }

}
