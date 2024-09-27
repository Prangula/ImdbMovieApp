package com.imdbmovieapp.domain.model

data class SearchMoviesDomain(
    val page: Int,
    val results: List<MoviesResultsDomain>,
    val totalPages: Int,
    val totalResults: Int
)
