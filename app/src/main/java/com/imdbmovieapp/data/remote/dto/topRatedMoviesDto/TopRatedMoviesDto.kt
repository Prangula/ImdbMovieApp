package com.imdbmovieapp.data.remote.dto.topRatedMoviesDto

import com.google.gson.annotations.SerializedName
import com.imdbmovieapp.data.remote.dto.popularMoviesDto.MoviesResultDto

data class TopRatedMoviesDto(
    val results: List<MoviesResultDto>,
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)