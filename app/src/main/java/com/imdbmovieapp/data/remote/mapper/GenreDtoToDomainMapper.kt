package com.imdbmovieapp.data.remote.mapper

import com.imdbmovieapp.data.remote.dto.GenreDto
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.utils.base.BaseMapper

class GenreDtoToDomainMapper : BaseMapper<GenreDto, GenreMoviesDomain> {
    override fun mapModel(model: GenreDto): GenreMoviesDomain {
        return with(model) {
            GenreMoviesDomain(
                id = id,
                name = name
            )
        }
    }
}