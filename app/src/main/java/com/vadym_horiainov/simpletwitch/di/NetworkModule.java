package com.vadym_horiainov.simpletwitch.di;

import com.vadym_horiainov.simpletwitch.BuildConfig;
import com.vadym_horiainov.simpletwitch.data.api.StreamApi;
import com.vadym_horiainov.simpletwitch.di.annotations.ApiUrl;
import com.vadym_horiainov.simpletwitch.di.annotations.UsherUrl;

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
    @ApiUrl
    public StreamApi provideStreamApi(@ApiUrl Retrofit retrofit) {
        return retrofit.create(StreamApi.class);
    }

    @Singleton
    @Provides
    @UsherUrl
    public StreamApi provideUsherApi(@UsherUrl Retrofit retrofit) {
        return retrofit.create(StreamApi.class);
    }

    @Singleton
    @Provides
    @ApiUrl
    public Retrofit provideRetrofit(@ApiUrl String baseUrl, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    @Singleton
    @Provides
    @UsherUrl
    public Retrofit provideUsherRetrofit(@UsherUrl String baseUrl, Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
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

    @Singleton
    @Provides
    @ApiUrl
    public String provideApiUrl() {
        return BuildConfig.API_URL;
    }

    @Singleton
    @Provides
    @UsherUrl
    public String provideUsherUrl() {
        return BuildConfig.USHER_URL;
    }
}
