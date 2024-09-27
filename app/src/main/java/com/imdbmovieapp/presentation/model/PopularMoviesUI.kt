package com.imdbmovieapp.presentation.model

data class PopularMoviesUI(
    val results: List<MoviesResultsUI>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)