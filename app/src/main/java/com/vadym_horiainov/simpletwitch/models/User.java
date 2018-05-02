package com.vadym_horiainov.simpletwitch.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class User {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    @SerializedName("_id")
    private int id;

    @SerializedName("bio")
    private String bio;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    private String createdAt;

    @ColumnInfo(name = "display_name")
    @SerializedName("display_name")
    private String displayName;

    @SerializedName("email")
    private String email;

    @ColumnInfo(name = "email_verified")
    @SerializedName("email_verified")
    private boolean emailVerified;

    @SerializedName("logo")
    private String logo;

    @SerializedName("name")
    private String name;

    @SerializedName("partnered")
    private boolean partnered;

    @ColumnInfo(name = "twitter_connected")
    @SerializedName("twitter_connected")
    private boolean twitterConnected;

    @SerializedName("type")
    private String type;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPartnered() {
        return partnered;
    }

    public void setPartnered(boolean partnered) {
        this.partnered = partnered;
    }

    public boolean isTwitterConnected() {
        return twitterConnected;
    }

    public void setTwitterConnected(boolean twitterConnected) {
        this.twitterConnected = twitterConnected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
