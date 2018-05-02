package com.vadym_horiainov.simpletwitch.mvvm.login;

import android.app.Application;
import android.util.Log;

import com.vadym_horiainov.simpletwitch.data.UserRepository;
import com.vadym_horiainov.simpletwitch.mvvm.base.ActivityViewModel;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class LoginActivityVM extends ActivityViewModel {
    private final UserRepository userRepository;
    private final SchedulerProvider schedulerProvider;

    @Inject
    LoginActivityVM(Application appContext, UserRepository userRepository,
                    SchedulerProvider schedulerProvider) {
        super(appContext);
        this.userRepository = userRepository;
        this.schedulerProvider = schedulerProvider;
    }


    public void accessTokenReceived(String url) {
        String accessToken = getAccessTokenFromURL(url);
        userRepository.saveToken(accessToken);
        getCompositeDisposable().add(
                userRepository.getUser(accessToken)
                        .observeOn(schedulerProvider.ui())
                        .subscribe(userRepository::saveUser,
                                throwable -> Log.e(TAG, "accessTokenReceived: ", throwable)));
    }

    private String getAccessTokenFromURL(String url) {
        Pattern pattern = Pattern.compile("access_token=([A-Za-z0-9]+?)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        return null;
    }
}
