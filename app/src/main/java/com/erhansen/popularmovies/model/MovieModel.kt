package com.erhansen.popularmovies.model

import com.google.gson.annotations.SerializedName


data class MovieModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)