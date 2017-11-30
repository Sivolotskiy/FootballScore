package com.footballscore.footballscore.injection.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.footballscore.footballscore.R;
import com.footballscore.footballscore.net.ApiManager;
import com.footballscore.footballscore.net.ApiService;
import com.footballscore.footballscore.net.OauthInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @NonNull
    @Singleton
    public ApiManager provideApiModule(@NonNull Context appContext, ApiService service) {
        return new ApiManager(appContext, service);
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull Context appContext, Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(appContext.getString(R.string.api))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(@NonNull Context appContext) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OauthInterceptor interceptor = new OauthInterceptor(appContext);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(interceptor)
                .build();
    }
}
