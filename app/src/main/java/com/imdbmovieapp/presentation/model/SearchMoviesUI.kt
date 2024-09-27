package com.imdbmovieapp.presentation.model

data class SearchMoviesUI(
    val page: Int,
    val results: List<MoviesResultsUI>,
    val totalPages: Int,
    val totalResults: Int
)
