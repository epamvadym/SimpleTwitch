package com.vadym_horiainov.simpletwitch.mvvm.login;

import com.vadym_horiainov.simpletwitch.di.annotations.OauthUrl;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    @OauthUrl
    String provideOauthUrl() {
        return "https://api.twitch.tv/kraken/oauth2/authorize?response_type=token&client_id=0s4cg0hmn8rq4rrv4ex8rtkexoape7&redirect_uri=http://localhost&&scope=user_read+chat_login+user_follows_edit+user_subscriptions";
    }

}
