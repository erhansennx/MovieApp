package com.erhansen.popularmovies.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erhansen.popularmovies.model.MovieModel
import com.erhansen.popularmovies.model.WatchlistModel
import java.lang.Exception

class WatchlistViewModel() : ViewModel() {

    val watchlistMovies = MutableLiveData<ArrayList<WatchlistModel>>()
    val watchlistMoviesError = MutableLiveData<Boolean>()
    val watchlistMoviesLoading = MutableLiveData<Boolean>()

    private val watchArrayList = ArrayList<WatchlistModel>()

    fun getWatchlistMovies(context:Context) {
        try {
            val sqLiteDatabase = context.openOrCreateDatabase("Watchlist Movies", Context.MODE_PRIVATE, null)
            val cursor = sqLiteDatabase.rawQuery("SELECT * FROM watchlistMovies",null)
            val movieTitleIndex = cursor.getColumnIndex("movieTitle")
            val moviePosterUrlIndex = cursor.getColumnIndex("moviePosterUrl")
            val movieRateIndex = cursor.getColumnIndex("movieRate")

            while (cursor.moveToNext()) {
                val movieTitle = cursor.getString(movieTitleIndex)
                val moviePosterUrl = cursor.getString(moviePosterUrlIndex)
                val movieRate = cursor.getString(movieRateIndex)
                val watchlistModel = WatchlistModel(movieTitle,moviePosterUrl,movieRate)
                watchArrayList.add(watchlistModel)
                println("get : $movieTitle $moviePosterUrl $movieRate")
            }
            watchlistMovies.value = watchArrayList
            cursor.close()
        } catch (exception: Exception) {
            exception.printStackTrace()
            println("exc : ${exception.message}")
        }
    }



}