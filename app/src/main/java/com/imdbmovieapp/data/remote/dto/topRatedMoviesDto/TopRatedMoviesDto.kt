package com.imdbmovieapp.data.remote.dto.topRatedMoviesDto

import com.imdbmovieapp.data.remote.dto.popularMoviesDto.MoviesResult

data class TopRatedMoviesDto(
    val page: Int,
    val results: List<MoviesResult>,
    val total_pages: Int,
    val total_results: Int
)