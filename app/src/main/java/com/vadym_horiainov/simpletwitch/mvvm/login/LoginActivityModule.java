package com.vadym_horiainov.simpletwitch.mvvm.login;

import com.vadym_horiainov.simpletwitch.di.annotations.OauthUrl;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    @OauthUrl
    String provideOauthUrl() {
        return "https://id.twitch.tv/oauth2/authorize?response_type=token&client_id=0s4cg0hmn8rq4rrv4ex8rtkexoape7&redirect_uri=http://localhost&scope=viewing_activity_read&state=c3ab8aa609ea11e793ae92361f002671";
    }

}
