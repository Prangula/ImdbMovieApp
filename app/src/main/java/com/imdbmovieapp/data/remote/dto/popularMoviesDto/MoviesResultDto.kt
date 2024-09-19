package com.imdbmovieapp.data.remote.dto.popularMoviesDto

data class MoviesResult(
    val id: Int,
    val genreIds: List<Int>,
    val posterPath: String,
    val releaseDate: String,
    val title: String
)