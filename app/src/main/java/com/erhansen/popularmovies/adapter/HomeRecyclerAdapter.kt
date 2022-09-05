package com.erhansen.popularmovies.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erhansen.popularmovies.databinding.HomeRecyclerRowBinding
import com.erhansen.popularmovies.model.Result
import com.erhansen.popularmovies.utils.Constants
import com.erhansen.popularmovies.utils.downloadImage
import com.erhansen.popularmovies.utils.placeHolderLoadingBar
import com.erhansen.popularmovies.view.MovieDetailsActivity

class HomeRecyclerAdapter(private val context: Context, private val popularMovieList: ArrayList<Result>) : RecyclerView.Adapter<HomeRecyclerAdapter.ItemHolder>(){

    class ItemHolder(val binding: HomeRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = HomeRecyclerRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.movieTitleText.text = popularMovieList[position].title
        holder.binding.movieReleaseDateText.text = "Release Date: ${popularMovieList[position].release_date}"
        holder.binding.movieVoteText.text = "Vote: ${popularMovieList[position].vote_average}"
        holder.binding.movieLanguageText.text = "Language: ${popularMovieList[position].original_language}"
        holder.binding.movieImageView.downloadImage("${Constants.IMAGE_BASEURL}${popularMovieList[position].poster_path}", placeHolderLoadingBar(context))
        holder.binding.movieDetailsButton.setOnClickListener {
            val intent = Intent(context,MovieDetailsActivity::class.java)
            intent.putExtra("Movie Image",popularMovieList[position].poster_path)
            intent.putExtra("Movie Backdrop Image",popularMovieList[position].backdrop_path)
            intent.putExtra("Movie Title", popularMovieList[position].title)
            intent.putExtra("Movie Release Date", popularMovieList[position].release_date)
            intent.putExtra("Movie Vote", popularMovieList[position].vote_average)
            intent.putExtra("Movie Language", popularMovieList[position].original_language)
            intent.putExtra("Movie Overview", popularMovieList[position].overview)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularMovieList.size
    }


}