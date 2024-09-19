package com.imdbmovieapp.data.remote.dto.popularMoviesDto

data class PopularMoviesDto(
    val moviesResultDtos: List<MoviesResultDto>,
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)