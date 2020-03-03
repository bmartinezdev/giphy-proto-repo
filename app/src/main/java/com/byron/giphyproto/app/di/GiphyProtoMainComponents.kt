package com.byron.giphyproto.app.di

import com.byron.giphyproto.app.GiphyProtoApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBindingModule::class,
    FragmentModule::class,
    NetworkModule::class])
interface GiphyProtoMainComponents: AndroidInjector<GiphyProtoApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<GiphyProtoApplication>()
}

