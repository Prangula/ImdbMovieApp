package com.imdbmovieapp.data.remote.dto.detailMovieDto

import com.imdbmovieapp.data.remote.dto.genreMovieDto.GenreDto

data class MovieDetailDto(
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