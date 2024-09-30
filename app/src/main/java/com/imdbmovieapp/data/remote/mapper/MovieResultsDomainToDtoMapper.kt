package com.imdbmovieapp.data.remote.mapper

import com.imdbmovieapp.data.remote.dto.MoviesResultDto
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.utils.base.BaseMapper

class MovieResultsDomainToDtoMapper : BaseMapper<MoviesResultsDomain, MoviesResultDto> {
    override fun mapModel(model: MoviesResultsDomain): MoviesResultDto {
        return with(model) {
            MoviesResultDto(
                id = id,
                genreIds = genreIds,
                posterPath = posterPath!!,
                releaseDate = releaseDate,
                title = title,
                overview = overview,
                backdropPath = backdropPath!!,
                voteAverage = voteAverage,
                isFavorite = isFavorite,
                heartColor = heartColor
            )
        }
    }
}