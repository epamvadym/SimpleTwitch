package com.vadym_horiainov.simpletwitch.data;

import com.vadym_horiainov.simpletwitch.BuildConfig;
import com.vadym_horiainov.simpletwitch.data.api.UserApi;
import com.vadym_horiainov.simpletwitch.data.db.UserDao;
import com.vadym_horiainov.simpletwitch.data.prefs.PreferencesHelper;
import com.vadym_horiainov.simpletwitch.models.User;
import com.vadym_horiainov.simpletwitch.util.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class UserRepository {
    private final UserApi userApi;
    private final UserDao userDao;
    private final PreferencesHelper preferencesHelper;
    private final SchedulerProvider schedulerProvider;

    @Inject
    UserRepository(UserApi userApi, UserDao userDao, PreferencesHelper preferencesHelper, SchedulerProvider schedulerProvider) {
        this.userApi = userApi;
        this.userDao = userDao;
        this.preferencesHelper = preferencesHelper;
        this.schedulerProvider = schedulerProvider;
    }

    public void saveToken(String accessToken) {
        preferencesHelper.saveToken(accessToken);
    }

    public String getToken() {
        return preferencesHelper.getToken();
    }

    public Single<User> fetchUser(String accessToken) {
        return userApi.getUserInfo(BuildConfig.CLIENT_ID, accessToken)
                .subscribeOn(schedulerProvider.io());
    }

    public Flowable<User> getUser() {
        return userDao.getUser();
    }

    public void saveUser(User user) {
        userDao.insert(user);
    }

    public void clearUser(User user) {
        userDao.delete(user);
    }
}
