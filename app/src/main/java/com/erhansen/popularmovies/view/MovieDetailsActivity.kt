package com.erhansen.popularmovies.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.erhansen.popularmovies.databinding.ActivityMovieDetailsBinding
import com.erhansen.popularmovies.utils.Constants
import com.erhansen.popularmovies.utils.downloadImage
import com.erhansen.popularmovies.utils.placeHolderLoadingBar

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    private var movieTitle: String? = null
    private var moviePosterUrl: String? = null
    private var movieRate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        movieTitle = intent.getStringExtra("Movie Title")
        moviePosterUrl = "${Constants.IMAGE_BASEURL}${intent.getStringExtra("Movie Image")}"
        movieRate = "${intent.getDoubleExtra("Movie Vote",0.0)}"

        with(binding) {
            movieBackdropImageView.downloadImage("${Constants.IMAGE_BASEURL}${intent.getStringExtra("Movie Backdrop Image")}",
                placeHolderLoadingBar(this@MovieDetailsActivity))
            moviePosterImageView.downloadImage(moviePosterUrl,
                placeHolderLoadingBar(this@MovieDetailsActivity))
            movieDetailTitleText.text = movieTitle
            movieDetailReleaseDateText.text = intent.getStringExtra("Movie Release Date")
            movieDetailVoteText.text = "Vote: $movieRate"
           //movieDetailLanguageText.text = "Language: ${intent.getStringExtra("Movie Language")}"
            movieOverviewText.text = intent.getStringExtra("Movie Overview")

            addToWatchlist.setOnClickListener {
                saveWatchlistMovies(movieTitle!!, moviePosterUrl!!, movieRate!!)
                Toast.makeText(this@MovieDetailsActivity,"Added to Watchlist!",Toast.LENGTH_SHORT).show()
            }
        }

    }



    private fun saveWatchlistMovies(movieTitle: String, moviePosterUrl: String, movieRate: String) {
        try {
            val sqLiteDatabase = this.openOrCreateDatabase("Watchlist Movies",
                Context.MODE_PRIVATE,null)
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS watchlistMovies(id INTEGER PRIMARY KEY," +
                    "movieTitle VARCHAR, moviePosterUrl VARCHAR, movieRate VARCHAR)")
            val sqLString = "INSERT INTO watchlistMovies(movieTitle, moviePosterUrl, movieRate) VALUES(?,?,?)"
            var sqLiteStatement = sqLiteDatabase.compileStatement(sqLString)
            sqLiteStatement.bindString(1,movieTitle)
            sqLiteStatement.bindString(2,moviePosterUrl)
            sqLiteStatement.bindString(3,movieRate)
            sqLiteStatement.execute()
            println("Added : $movieTitle , $moviePosterUrl , $movieRate")
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

}