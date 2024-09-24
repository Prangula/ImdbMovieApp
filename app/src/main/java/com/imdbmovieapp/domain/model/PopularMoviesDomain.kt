package com.imdbmovieapp.domain.model

import com.imdbmovieapp.data.remote.dto.MoviesResultDto

data class PopularMoviesDomain(
    val results: List<MoviesResultDto>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)