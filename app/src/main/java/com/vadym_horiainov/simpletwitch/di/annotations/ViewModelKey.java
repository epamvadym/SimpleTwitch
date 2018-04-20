package com.vadym_horiainov.simpletwitch.di.annotations;

import android.arch.lifecycle.ViewModel;

import dagger.MapKey;

@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}
