package com.byron.giphyproto.app.di

import com.byron.giphyproto.app.ui.views.main.MainActivity
import com.byron.giphyproto.app.ui.views.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributeMainActivityModule(): MainActivity

}