package com.imdbmovieapp.data.remote.dto.detailMovieDto

data class MovieDetailDto(
    val movieGenreDtos: List<MovieGenreDto>,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)