package com.imdbmovieapp.data.remote.mapper

import com.imdbmovieapp.data.remote.dto.MoviesResponseDto
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.utils.base.BaseMapper

class MoviesResponseDtoToDomainMapper : BaseMapper<MoviesResponseDto, MoviesResponseDomain> {
    override fun mapModel(model: MoviesResponseDto): MoviesResponseDomain {
        return with(model) {
            MoviesResponseDomain(
                results = results.map {
                    MoviesResultsDomain(
                        id = it.id,
                        genreIds = it.genreIds,
                        posterPath = it.posterPath,
                        releaseDate = it.releaseDate,
                        title = it.title,
                        overview = it.overview,
                        backdropPath = it.backdropPath,
                        voteAverage = it.voteAverage,
                        isFavorite = it.isFavorite,
                        heartColor = it.heartColor
                    )
                },
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}