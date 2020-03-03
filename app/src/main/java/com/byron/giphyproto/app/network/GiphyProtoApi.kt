package com.byron.giphyproto.app.network

import com.byron.giphyproto.app.common.API_KEY
import com.byron.giphyproto.app.data.model.GiphyResponse
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphyProtoApi {

    @GET("/v1/gifs/trending?api_key=$API_KEY")
    fun getTrendingResults(
        @Query("limit") limit: Int,
        @Query("rating") rating: String
    ): Flowable<Response<GiphyResponse>>


    @GET("/v1/gifs/search?api_key=$API_KEY")
    fun getSearchResults(
        @Query("q") tag: String,
        @Query("limit") limit: Int,
        @Query("rating") rating: String
    ): Flowable<Response<GiphyResponse>>
}