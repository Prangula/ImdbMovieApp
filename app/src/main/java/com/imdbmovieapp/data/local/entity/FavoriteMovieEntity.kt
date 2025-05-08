package com.imdbmovieapp.data.local.entity

data class FavoriteMovieEntity(
    val id: Int,
    val title: String,
    val year: String,
    val genre: String,
    val image: String,
    val favoriteMovie: Int,
)