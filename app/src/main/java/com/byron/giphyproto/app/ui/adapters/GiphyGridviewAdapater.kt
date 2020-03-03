package com.byron.giphyproto.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.byron.giphyproto.app.R
import com.byron.giphyproto.app.data.model.GiphyResponse
import com.byron.giphyproto.app.databinding.LayoutGridItemBinding


class GiphyGridviewAdapater(private var giphyDataList: List<GiphyResponse.GiphyData>) : RecyclerView.Adapter<GiphyGridviewAdapater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_grid_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return giphyDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.imageData = giphyDataList[position]
        holder.binding?.imageFormat = giphyDataList[position].images
    }

    fun updateAdapter(data: List<GiphyResponse.GiphyData>) {
        giphyDataList = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: LayoutGridItemBinding? = DataBindingUtil.bind(view)
        init {
            view.tag = binding
        }
    }

}