package com.erhansen.popularmovies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erhansen.popularmovies.databinding.HomeRecyclerRowBinding

class HomeRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<HomeRecyclerAdapter.ItemHolder>(){

    class ItemHolder(val binding: HomeRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = HomeRecyclerRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.movieTitleText.text = "Thor: Love and Thunder"
        holder.binding.movieReleaseDateText.text = "Release Date: 2022-07-06"
        holder.binding.movieVoteText.text = "Vote: 6.8"
        holder.binding.movieLanguageText.text = "Language: EN"
    }

    override fun getItemCount(): Int {
        return 20
    }


}