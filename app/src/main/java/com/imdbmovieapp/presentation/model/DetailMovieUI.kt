package com.imdbmovieapp.presentation.model

data class DetailMovieUI(
    val id: Int,
    val genreDtos: List<GenreResultsUI>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
)