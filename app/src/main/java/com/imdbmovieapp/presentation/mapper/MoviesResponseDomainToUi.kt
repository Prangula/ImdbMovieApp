package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.model.MoviesResponseUI
import com.imdbmovieapp.utils.base.BaseMapper

class MoviesResponseDomainToUi : BaseMapper<MoviesResponseDomain, MoviesResponseUI> {
    override fun mapModel(model: MoviesResponseDomain): MoviesResponseUI {
        return with(model) {
            MoviesResponseUI(
                results = results.map {
                    MoviesResultsUI(
                        id = it.id,
                        genreIds = it.genreIds,
                        posterPath = it.posterPath.orEmpty(),
                        releaseDate = it.releaseDate,
                        title = it.title,
                        overview = it.overview,
                        backdropPath = it.backdropPath.orEmpty(),
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