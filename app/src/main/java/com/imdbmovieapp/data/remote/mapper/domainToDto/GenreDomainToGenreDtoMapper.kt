package com.imdbmovieapp.data.remote.mapper.domainToDto

import com.imdbmovieapp.data.remote.dto.genreMovieDto.GenreMovieDto
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class GenreDomainToGenreDtoMapper : BaseMapper<GenreMoviesDomain, GenreMovieDto> {
    override fun mapModel(model: GenreMoviesDomain): GenreMovieDto {
        return with(model) {
            GenreMovieDto(
                genres = genres
            )
        }
    }
}