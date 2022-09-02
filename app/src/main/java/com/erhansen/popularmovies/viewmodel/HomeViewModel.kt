package com.erhansen.popularmovies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

    val popularMovies = MutableLiveData<MovieModel>()
    val popularMoviesError = MutableLiveData<Boolean>()
    val popularMoviesLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        disposable.add(
            movieAPIService.getMovieData()
                .subscribeOn(Schedulers.newThread()) //yeni thread'de thread ? async şekilde yapılacak.
                .observeOn(AndroidSchedulers.mainThread()) //nerede gözlemleceği
                .subscribeWith(object : DisposableSingleObserver<MovieModel>() {
                    override fun onSuccess(t: MovieModel) {
                        popularMovies.value = t
                        popularMoviesError.value = false
                        popularMoviesLoading.value = false
                        for (i in t.results){
                            println("Movie Title : ${i.title}")
                        }
                    }
                    override fun onError(e: Throwable) {
                        popularMoviesError.value = true
                        popularMoviesLoading.value = false
                        println("Get Data From API Error: ${e.message} ")
                    }
                })
        )
    }

}