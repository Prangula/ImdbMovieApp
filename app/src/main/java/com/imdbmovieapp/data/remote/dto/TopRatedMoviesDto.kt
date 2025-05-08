package com.imdbmovieapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TopRatedMoviesDto(
    val results: List<MoviesResultDto>,
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)