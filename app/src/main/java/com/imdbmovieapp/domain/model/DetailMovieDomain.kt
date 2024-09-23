package com.imdbmovieapp.domain.model

import com.imdbmovieapp.data.remote.dto.GenreDto

data class DetailMovieDomain(
    val id: Int,
    val genreDtos: List<GenreDto>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)