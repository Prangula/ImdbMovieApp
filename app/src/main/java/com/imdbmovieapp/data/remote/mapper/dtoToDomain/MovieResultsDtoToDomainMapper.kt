package com.imdbmovieapp.data.remote.mapper.dtoToDomain

import com.imdbmovieapp.data.remote.dto.MoviesResultDto
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.utils.BaseMapper

class MovieResultsDtoToDomainMapper : BaseMapper<MoviesResultDto, MoviesResultsDomain> {
    override fun mapModel(model: MoviesResultDto): MoviesResultsDomain {
        return with(model) {
            MoviesResultsDomain(
                id = id,
                genreIds = genreIds,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title
            )
        }
    }
}