package com.imdbmovieapp.data.remote.dto.searchMoviesDto

data class SearchMoviesDto(
    val page: Int,
    val results: List<Any>,
    val total_pages: Int,
    val total_results: Int
)