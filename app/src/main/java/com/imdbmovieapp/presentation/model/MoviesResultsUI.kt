package com.imdbmovieapp.presentation.model

data class MoviesResultsUI(
    val id: Int,
    val genreIds: List<Int>,
    val posterPath: String,
    val releaseDate: String,
    val title: String
)
