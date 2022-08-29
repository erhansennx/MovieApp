package com.erhansen.popularmovies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.erhansen.popularmovies.model.MovieModel
import com.erhansen.popularmovies.service.MovieAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val movieAPIService = MovieAPIService()

    fun getDataFromAPI() {
        disposable.add(
            movieAPIService.getMovieData()
                .subscribeOn(Schedulers.newThread()) //yeni thread'de thread ? async şekilde yapılacak.
                .observeOn(AndroidSchedulers.mainThread()) //nerede gözlemleceği
                .subscribeWith(object : DisposableSingleObserver<MovieModel>() {
                    override fun onSuccess(t: MovieModel) {
                        for (i in t.results) {
                            println("Title : ${i.title}")
                        }
                    }
                    override fun onError(e: Throwable) {
                        Log.d("Error",e.message.toString())
                    }
                })
        )
    }

}