package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.data.api.StreamApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public StreamApi provideStreamApi(Retrofit retrofit) {
        return retrofit.create(StreamApi.class);
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl("https://api.twitch.tv/kraken/")
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    @Singleton
    @Provides
    public Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}
