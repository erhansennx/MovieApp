package com.erhansen.popularmovies.service

import com.erhansen.popularmovies.model.MovieModel
import com.erhansen.popularmovies.utils.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MovieAPIService {

    //Retrofit Object
    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //we specify that we will use gson
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //we specify that we will use rxjava
        .build()
        .create(MovieAPI::class.java) //we bind movie api and service

    fun getMovieData() : Single<MovieModel> {
        return api.getMovies()
    }


}