package com.imdbmovieapp.presentation.model

import android.os.Parcelable
import com.imdbmovieapp.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesResultsUI(
    var id: Int,
    var genreIds: List<Int>,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val voteAverage: Double,
    var isFavorite: Boolean = false,
    var heartColor: Int = R.drawable.ic_uncolored_heart
) : Parcelable
