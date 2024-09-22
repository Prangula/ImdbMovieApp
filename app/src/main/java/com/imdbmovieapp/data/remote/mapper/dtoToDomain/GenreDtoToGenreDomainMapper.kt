package com.imdbmovieapp.data.remote.mapper.dtoToDomain

import com.imdbmovieapp.data.remote.dto.movieGenreDto.MovieGenreDto
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class GenreDtoToGenreDomainMapper : BaseMapper<MovieGenreDto, GenreMoviesDomain> {
    override fun mapModel(model: MovieGenreDto): GenreMoviesDomain {
        return GenreMoviesDomain(
            genres = model.genres
        )
    }
}