package com.vadym_horiainov.simpletwitch.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.vadym_horiainov.simpletwitch.di.annotations.ViewModelKey;
import com.vadym_horiainov.simpletwitch.mvvm.ViewModelProviderFactory;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.item.PlayStreamActivityVM;
import com.vadym_horiainov.simpletwitch.mvvm.live_streams.list.LiveStreamsActivityVM;
import com.vadym_horiainov.simpletwitch.mvvm.login.LoginActivityVM;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LiveStreamsActivityVM.class)
    abstract ViewModel bindLiveStreamsActivityViewModel(LiveStreamsActivityVM liveStreamsActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PlayStreamActivityVM.class)
    abstract ViewModel bindPlayStreamActivityViewModel(PlayStreamActivityVM playStreamActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginActivityVM.class)
    abstract ViewModel bindLoginActivityViewModel(LoginActivityVM loginActivityViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);
}
