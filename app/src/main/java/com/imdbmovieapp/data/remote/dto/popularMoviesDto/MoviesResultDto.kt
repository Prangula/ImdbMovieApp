package com.imdbmovieapp.data.remote.dto.popularMoviesDto

data class MoviesResultDto(
    val id: Int,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String
)