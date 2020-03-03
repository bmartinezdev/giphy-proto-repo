package com.byron.giphyproto.app.data.model

import com.google.gson.annotations.SerializedName

data class GiphyResponse(val data: List<GiphyData>, val meta: Meta, val pagination: Pagination) {

    data class GiphyData(val id: String, val images: ImageFormats)

    data class ImageFormats(@SerializedName("fixed_height") val original: GiphyImage,
                            @SerializedName("fixed_width") val fixedWidth: GiphyImage,
                            @SerializedName("fixed_width_still") val fixedWidthStill: GiphyImage)

    data class GiphyImage(val url: String, val webp: String, val width: Int, val height: Int)

    data class Meta(val status: Int)

    data class Pagination(@SerializedName("total_count") val totalCount: Int, val count: Int, val offset: Int)
}
