package com.erhansen.popularmovies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Result(
    @SerializedName("adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>,
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val original_language: String,
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val original_title: String,
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val poster_path: String,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val release_date: String,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}