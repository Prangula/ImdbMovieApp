package com.imdbmovieapp.presentation.model

data class MoviesResponseUI(
    val results: List<MoviesResultsUI>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)