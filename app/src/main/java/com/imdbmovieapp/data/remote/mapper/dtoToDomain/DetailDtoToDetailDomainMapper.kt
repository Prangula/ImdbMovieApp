package com.imdbmovieapp.data.remote.mapper.dtoToDomain

import com.imdbmovieapp.data.remote.dto.MovieDetailsDto
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.utils.BaseMapper

class DetailDtoToDetailDomainMapper : BaseMapper<MovieDetailsDto, DetailMovieDomain> {
    override fun mapModel(model: MovieDetailsDto): DetailMovieDomain {
        return with(model) {
            DetailMovieDomain(
                id = id,
                genreDtos = genreDtos,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage
            )
        }
    }
}