package com.imdbmovieapp.presentation.model

data class FavoriteMovieUI(
    val id: Int,
    val title: String,
    val year: String,
    val genre: String,
    val image: String,
    val favoriteMovie: Int,
)
