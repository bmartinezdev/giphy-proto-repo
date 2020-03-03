package com.byron.giphyproto.app

import com.byron.giphyproto.app.di.DaggerGiphyProtoMainComponents
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GiphyProtoApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerGiphyProtoMainComponents.builder().create(this)
    }
}