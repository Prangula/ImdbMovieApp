package com.imdbmovieapp.data.remote.mapper.domainToDto

import com.imdbmovieapp.data.remote.dto.movieGenreDto.MovieGenreDto
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class GenreDomainToGenreDtoMapper : BaseMapper<GenreMoviesDomain, MovieGenreDto> {
    override fun mapModel(model: GenreMoviesDomain): MovieGenreDto {
        return MovieGenreDto(
            genres = model.genres
        )
    }
}