package com.imdbmovieapp.data.remote.dto.topRatedMoviesDto

data class TopRatedMoviesDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)