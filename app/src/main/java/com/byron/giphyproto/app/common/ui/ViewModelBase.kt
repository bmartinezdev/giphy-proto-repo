package com.byron.giphyproto.app.common.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class ViewModelBase : ViewModel() {

    private val disposables = CompositeDisposable()

    protected open fun addDisposable(disposable: Disposable?) {
        disposables.add(disposable!!)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}