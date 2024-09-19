package com.imdbmovieapp.domain.model

data class SearchMoviesDomain(
    val page: Int,
    val results: List<Any>,
    val totalPages: Int,
    val totalResults: Int
)
