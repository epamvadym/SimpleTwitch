package com.vadym_horiainov.simpletwitch.mvvm.login;

import android.net.Uri;

import com.vadym_horiainov.simpletwitch.BuildConfig;
import com.vadym_horiainov.simpletwitch.di.annotations.OauthUrl;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    @OauthUrl
    String provideOauthUrl() {
        // https://api.twitch.tv/kraken/oauth2/authorize?response_type=token&client_id=0s4cg0hmn8rq4rrv4ex8rtkexoape7
        // &redirect_uri=http://localhost&&scope=user_read+chat_login+user_follows_edit+user_subscriptions
        return new Uri.Builder()
                .scheme("https")
                .authority("api.twitch.tv")
                .appendPath("kraken")
                .appendPath("oauth2")
                .appendPath("authorize")
                .appendQueryParameter("response_type", "token")
                .appendQueryParameter("client_id", BuildConfig.CLIENT_ID)
                .appendQueryParameter("redirect_uri", "http://localhost")
                .appendQueryParameter("scope", "user_read chat_login user_follows_edit user_subscriptions")
                .build()
                .toString();
    }

}
