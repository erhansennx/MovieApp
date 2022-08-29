package com.erhansen.popularmovies.service

import com.erhansen.popularmovies.model.MovieModel
import com.erhansen.popularmovies.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface MovieAPI {
    //I'm download data with rxjava.
    //I'm getting data one time with use Single<>.
    @GET("${Constants.EXTENSION_URL}${Constants.API_KEY}")
    fun getMovies(): Single<MovieModel>
}