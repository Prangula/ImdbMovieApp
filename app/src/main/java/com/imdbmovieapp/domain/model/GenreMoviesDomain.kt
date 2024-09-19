package com.imdbmovieapp.domain.model

import com.imdbmovieapp.data.remote.dto.genreMovieDto.GenreDto

data class GenreMoviesDomain(
    val genres: List<GenreDto>
)
