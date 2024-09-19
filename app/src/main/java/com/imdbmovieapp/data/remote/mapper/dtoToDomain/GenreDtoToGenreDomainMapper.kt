package com.imdbmovieapp.data.remote.mapper.dtoToDomain

import com.imdbmovieapp.data.remote.dto.genreMovieDto.GenreMovieDto
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class GenreDtoToGenreDomainMapper : BaseMapper<GenreMovieDto, GenreMoviesDomain> {
    override fun mapModel(model: GenreMovieDto): GenreMoviesDomain {
        return with(model) {
            GenreMoviesDomain(
                genres = genres
            )
        }
    }
}