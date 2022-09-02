package com.erhansen.popularmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erhansen.popularmovies.R
import com.erhansen.popularmovies.databinding.ActivityMovieDetailsBinding
import com.erhansen.popularmovies.utils.Constants
import com.erhansen.popularmovies.utils.downloadImage
import com.erhansen.popularmovies.utils.placeHolderLoadingBar

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        with(binding) {
            movieBackdropImageView.downloadImage("${Constants.IMAGE_BASEURL}${intent.getStringExtra("Movie Backdrop Image")}",
                placeHolderLoadingBar(this@MovieDetailsActivity))
            moviePosterImageView.downloadImage("${Constants.IMAGE_BASEURL}${intent.getStringExtra("Movie Image")}",
                placeHolderLoadingBar(this@MovieDetailsActivity))
            movieDetailTitleText.text = intent.getStringExtra("Movie Title")
            movieDetailReleaseDateText.text = intent.getStringExtra("Movie Release Date")
            movieDetailVoteText.text = "Vote: ${intent.getDoubleExtra("Movie Vote",0.0)}"
           //movieDetailLanguageText.text = "Language: ${intent.getStringExtra("Movie Language")}"
            movieOverviewText.text = intent.getStringExtra("Movie Overview")
        }

    }



}