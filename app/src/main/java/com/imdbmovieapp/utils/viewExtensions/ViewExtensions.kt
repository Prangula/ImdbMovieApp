package com.imdbmovieapp.utils.viewExtensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.imdbmovieapp.R
import com.imdbmovieapp.data.remote.networkUtils.NetworkKeys.IMAGE_URL
import com.imdbmovieapp.presentation.model.MoviesResultsUI

fun ImageView.setImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_empty)
        .error(R.drawable.ic_empty)
        .into(this)
}

fun MoviesResultsUI.getPosterUrl(): String {
    return IMAGE_URL + posterPath
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}