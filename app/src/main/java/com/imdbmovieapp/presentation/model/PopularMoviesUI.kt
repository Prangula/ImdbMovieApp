package com.imdbmovieapp.presentation.model

import com.imdbmovieapp.data.remote.dto.MoviesResultDto

data class PopularMoviesUI(
    val moviesResultDtos: List<MoviesResultDto>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)