package com.imdbmovieapp.domain.model

data class MoviesResponseDomain(
    val results: List<MoviesResultsDomain>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)