package com.byron.giphyproto.app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.byron.giphyproto.app.common.LIMIT
import com.byron.giphyproto.app.common.RATING
import com.byron.giphyproto.app.common.ui.ViewModelBase
import com.byron.giphyproto.app.data.model.GiphyResponse
import com.byron.giphyproto.app.network.GiphyProtoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(giphyApi: GiphyProtoApi) : ViewModelBase() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private var mGiphyApi: GiphyProtoApi = giphyApi

    val giphyTrendingMutableLiveData = MutableLiveData<GiphyResponse>()

    init {
        val giphyResponse = mGiphyApi.getTrendingResults(LIMIT, RATING)
        addDisposable(giphyResponse.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::processResponse, this::processError))
    }


    fun doSearch(searchTerm: String) {
        if (searchTerm.isNotEmpty()) {
            val searchResponse = mGiphyApi.getSearchResults(searchTerm, LIMIT, RATING)
            addDisposable(searchResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::processResponse, this::processError))
        }
    }

    private fun processResponse(response: Response<GiphyResponse>) {
        if (response.isSuccessful) {
            var metaStatus = 0
            var totalCount = 0
            var count = 0
            var offset = 0
            val giphyBody = response.body()
            var giphyResponse: GiphyResponse? = null

            giphyBody?.let {
                giphyResponse = it
                metaStatus = it.meta.status
                totalCount = it.pagination.totalCount
                count = it.pagination.count
                offset = it.pagination.offset
            }

            giphyTrendingMutableLiveData.postValue(
                GiphyResponse(giphyResponse?.data!!,
                    GiphyResponse.Meta(metaStatus),
                    GiphyResponse.Pagination(totalCount, count, offset)
                )
            )
        }
    }

    private fun processError(throwable: Throwable) {
        Log.e(TAG, throwable.message)
    }
}
