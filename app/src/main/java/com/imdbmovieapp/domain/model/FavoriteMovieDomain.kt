package com.imdbmovieapp.domain.model

data class FavoriteMovieDomain(
    val id: Int,
    val title: String,
    val year: String,
    val genre: String,
    val image: String,
    val favoriteMovie: Int,
)
