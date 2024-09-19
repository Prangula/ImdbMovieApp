package com.imdbmovieapp.domain.model

import com.imdbmovieapp.data.remote.dto.popularMoviesDto.MoviesResultDto

data class PopularMoviesDomain(
    val moviesResultDtos: List<MoviesResultDto>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)