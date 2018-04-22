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
    public StreamApi provideStreamApi(@ApiUrl String baseUrl, Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl(baseUrl).build().create(StreamApi.class);
    }

    @Singleton
    @Provides
    @UsherUrl
    public StreamApi provideUsherApi(@UsherUrl String baseUrl, Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl(baseUrl).build().create(StreamApi.class);
    }

    @Singleton
    @Provides
    public Retrofit.Builder provideRetrofitBuilder(Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory);
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
