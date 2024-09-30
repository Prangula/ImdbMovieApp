package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.utils.base.BaseMapper

class MovieResultsUIToDomainMapper : BaseMapper<MoviesResultsUI, MoviesResultsDomain> {
    override fun mapModel(model: MoviesResultsUI): MoviesResultsDomain {
        return with(model) {
            MoviesResultsDomain(
                id = id,
                genreIds = genreIds,
                posterPath = posterPath.orEmpty(),
                releaseDate = releaseDate,
                title = title,
                overview = overview,
                backdropPath = backdropPath.orEmpty(),
                voteAverage = voteAverage,
                isFavorite = isFavorite,
                heartColor = heartColor
            )
        }
    }
}