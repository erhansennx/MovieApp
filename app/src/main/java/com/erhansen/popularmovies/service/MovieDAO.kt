package com.erhansen.popularmovies.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.erhansen.popularmovies.model.Result

@Dao
interface MovieDAO {

    @Insert
    suspend fun insertAll(vararg movies : Result) : List<Long> //Primary KEY dönecek.

    @Query("SELECT * FROM result")
    suspend fun getAllMovies() : List<Result>

    @Query("SELECT * FROM result WHERE uuid = :movieID")
    suspend fun getMovie(movieID : Int) : Result


}