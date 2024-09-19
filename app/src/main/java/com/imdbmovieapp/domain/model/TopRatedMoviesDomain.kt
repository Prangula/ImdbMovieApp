package com.imdbmovieapp.domain.model

import com.imdbmovieapp.data.remote.dto.popularMoviesDto.MoviesResultDto

data class TopRatedMoviesDomain(
    val results: List<MoviesResultDto>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)
