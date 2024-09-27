package com.imdbmovieapp.domain.model

data class TopRatedMoviesDomain(
    val results: List<MoviesResultsDomain>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)
