package com.imdbmovieapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
    val id: Int,
    val genreDtos: List<GenreDto>,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)