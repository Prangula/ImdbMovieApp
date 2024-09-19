package com.imdbmovieapp.data.remote.dto.searchMoviesDto

import com.google.gson.annotations.SerializedName

data class SearchMoviesDto(
    val page: Int,
    val results: List<Any>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)