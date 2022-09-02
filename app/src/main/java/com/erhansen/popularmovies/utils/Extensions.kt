package com.erhansen.popularmovies.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erhansen.popularmovies.R
import com.google.android.material.progressindicator.CircularProgressIndicator

fun ImageView.downloadImage(imageURL: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_launcher_background)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(imageURL)
        .into(this)

}

fun placeHolderLoadingBar(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 7f
        centerRadius = 35f
        start()
    }
}
