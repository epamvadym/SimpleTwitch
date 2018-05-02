package com.vadym_horiainov.simpletwitch.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.vadym_horiainov.simpletwitch.BuildConfig;
import com.vadym_horiainov.simpletwitch.data.db.AppDatabase;
import com.vadym_horiainov.simpletwitch.data.db.UserDao;
import com.vadym_horiainov.simpletwitch.data.prefs.PreferencesHelper;
import com.vadym_horiainov.simpletwitch.di.annotations.PreferenceInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, BuildConfig.DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    UserDao provideUserDao(AppDatabase database) {
        return database.userDao();
    }

    @Singleton
    @Provides
    PreferencesHelper providePreferencesHelper(Application application, @PreferenceInfo String prefFileName) {
        return new PreferencesHelper(application, prefFileName);
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return BuildConfig.PREF_NAME;
    }

}
