package com.byron.giphyproto.app.di

import android.content.Context
import com.byron.giphyproto.app.GiphyProtoApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    internal fun provideContext(application: GiphyProtoApplication): Context {
        return application
    }

}