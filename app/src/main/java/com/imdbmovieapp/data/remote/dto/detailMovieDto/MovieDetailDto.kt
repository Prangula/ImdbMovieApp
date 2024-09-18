package com.imdbmovieapp.data.remote.dto.detailMovieDto

import com.imdbmovieapp.data.remote.dto.genreMovieDto.Genre

data class MovieDetailDto(
    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)