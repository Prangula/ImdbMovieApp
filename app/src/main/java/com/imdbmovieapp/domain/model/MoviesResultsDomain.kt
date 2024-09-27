package com.imdbmovieapp.domain.model

data class MoviesResultsDomain(
    val id: Int,
    val genreIds: List<Int>,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val overview: String,
    val backdropPath: String?,
    val voteAverage:Double
)