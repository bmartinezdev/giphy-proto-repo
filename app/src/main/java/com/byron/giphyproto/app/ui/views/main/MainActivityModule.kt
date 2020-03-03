package com.byron.giphyproto.app.ui.views.main

import android.app.Activity
import dagger.Binds
import dagger.Module


@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindsActivity(activity: MainActivity?): Activity?

}