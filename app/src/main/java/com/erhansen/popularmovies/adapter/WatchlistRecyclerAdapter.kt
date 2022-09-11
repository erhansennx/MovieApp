package com.erhansen.popularmovies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erhansen.popularmovies.databinding.WatchlistRecyclerRowBinding
import com.erhansen.popularmovies.model.WatchlistModel
import com.erhansen.popularmovies.utils.downloadImage
import com.erhansen.popularmovies.utils.placeHolderLoadingBar

class WatchlistRecyclerAdapter(private val context: Context, private val watchArrayList: ArrayList<WatchlistModel>) : RecyclerView.Adapter<WatchlistRecyclerAdapter.ItemHolder>() {

    class ItemHolder(val watchlistRecyclerRowBinding: WatchlistRecyclerRowBinding) : RecyclerView.ViewHolder(watchlistRecyclerRowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val watchlistRecyclerRowBinding = WatchlistRecyclerRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemHolder(watchlistRecyclerRowBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.watchlistRecyclerRowBinding.watchlistMovieTitle.text = watchArrayList[position].movieTitle
        holder.watchlistRecyclerRowBinding.watchlistMovieImageView.downloadImage(
            watchArrayList[position].moviePosterUrl, placeHolderLoadingBar(context)
        )
        holder.watchlistRecyclerRowBinding.watchlistMovieRate.text = watchArrayList[position].movieRate
    }

    override fun getItemCount(): Int {
        return watchArrayList.size
    }


}