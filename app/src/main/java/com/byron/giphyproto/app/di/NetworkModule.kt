package com.byron.giphyproto.app.di

import com.byron.giphyproto.app.common.BASE_URL
import com.byron.giphyproto.app.network.GiphyProtoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideGiphyProtoApi(retrofit: Retrofit): GiphyProtoApi {
        return retrofit.create(GiphyProtoApi::class.java)
    }
}