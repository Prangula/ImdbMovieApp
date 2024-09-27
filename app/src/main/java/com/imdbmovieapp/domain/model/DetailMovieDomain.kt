package com.imdbmovieapp.domain.model

data class DetailMovieDomain(
    val id: Int,
    val genreDtos: List<GenreResultsDomain>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)