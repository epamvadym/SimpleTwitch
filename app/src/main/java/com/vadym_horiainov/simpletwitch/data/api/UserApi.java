package com.vadym_horiainov.simpletwitch.data.api;

import com.vadym_horiainov.simpletwitch.models.User;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface UserApi {

    @GET("kraken/user")
    Single<User> getUserInfo(@Header("Client-ID") String client, @Query("oauth_token") String token);

}
