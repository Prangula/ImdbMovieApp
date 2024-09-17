package com.imdbmovieapp.data.remote.dto.popularMoviesDto

data class PopularMoviesDto(
    val page: Int,
    val moviesResults: List<MoviesResult>,
    val total_pages: Int,
    val total_results: Int
)