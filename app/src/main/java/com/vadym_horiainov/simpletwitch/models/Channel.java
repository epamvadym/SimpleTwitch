package com.vadym_horiainov.simpletwitch.models;

import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("_id")
    private String id;

    @SerializedName("broadcaster_language")
    private String broadcasterLanguage;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("followers")
    private Integer followers;

    @SerializedName("game")
    private String game;

    @SerializedName("language")
    private String language;

    @SerializedName("logo")
    private String logo;

    @SerializedName("mature")
    private Boolean mature;

    @SerializedName("name")
    private String name;

    @SerializedName("partner")
    private Boolean partner;

    @SerializedName("profile_banner")
    private String profileBanner;

    @SerializedName("profile_banner_background_color")
    private Object profileBannerBackgroundColor;

    @SerializedName("status")
    private String status;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("url")
    private String url;

    @SerializedName("video_banner")
    private String videoBanner;

    @SerializedName("views")
    private Long views;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBroadcasterLanguage() {
        return broadcasterLanguage;
    }

    public void setBroadcasterLanguage(String broadcasterLanguage) {
        this.broadcasterLanguage = broadcasterLanguage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getMature() {
        return mature;
    }

    public void setMature(Boolean mature) {
        this.mature = mature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPartner() {
        return partner;
    }

    public void setPartner(Boolean partner) {
        this.partner = partner;
    }

    public String getProfileBanner() {
        return profileBanner;
    }

    public void setProfileBanner(String profileBanner) {
        this.profileBanner = profileBanner;
    }

    public Object getProfileBannerBackgroundColor() {
        return profileBannerBackgroundColor;
    }

    public void setProfileBannerBackgroundColor(Object profileBannerBackgroundColor) {
        this.profileBannerBackgroundColor = profileBannerBackgroundColor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoBanner() {
        return videoBanner;
    }

    public void setVideoBanner(String videoBanner) {
        this.videoBanner = videoBanner;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

}
