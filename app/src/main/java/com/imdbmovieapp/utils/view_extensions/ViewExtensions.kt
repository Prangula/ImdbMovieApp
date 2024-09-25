package com.imdbmovieapp.utils.view_extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.imdbmovieapp.R
import com.imdbmovieapp.data.remote.network_utils.NetworkKeys.IMAGE_URL
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