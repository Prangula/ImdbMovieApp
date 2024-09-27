package com.imdbmovieapp.domain.model

data class PopularMoviesDomain(
    val results: List<MoviesResultsDomain>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)