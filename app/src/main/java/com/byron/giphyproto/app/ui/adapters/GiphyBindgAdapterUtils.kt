package com.byron.giphyproto.app.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.byron.giphyproto.app.R


class GiphyBindgAdapterUtils {

    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(
            imageView: ImageView,
            imageUrl: String?
        ) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .fitCenter()
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .into(imageView)
        }
    }
}