package com.imdbmovieapp.data.remote.dto.popularMoviesDto

data class MoviesResult(
    val genre_ids: List<Int>,
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
)