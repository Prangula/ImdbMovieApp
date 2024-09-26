package com.imdbmovieapp.presentation.model

data class MoviesResultsUI(
    var id: Int,
    var genreIds: List<Int>,
    val posterPath: String,
    val releaseDate: String,
    val title: String
)
