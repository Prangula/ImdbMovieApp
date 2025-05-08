package com.imdbmovieapp.data.remote.mapper

import com.imdbmovieapp.data.remote.dto.MovieDetailsDto
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreResultsDomain
import com.imdbmovieapp.utils.base.BaseMapper

class DetailDtoToDomainMapper : BaseMapper<MovieDetailsDto, DetailMovieDomain> {
    override fun mapModel(model: MovieDetailsDto): DetailMovieDomain {
        return with(model) {
            DetailMovieDomain(
                id = id,
                genreDtos = genreDtos.map {
                    GenreResultsDomain(
                        it.id,
                        it.name
                    )
                },
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage
            )
        }
    }
}