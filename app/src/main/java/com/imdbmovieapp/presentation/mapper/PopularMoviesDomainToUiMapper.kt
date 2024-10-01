package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.model.PopularMoviesUI
import com.imdbmovieapp.utils.base.BaseMapper

class PopularMoviesDomainToUiMapper : BaseMapper<PopularMoviesDomain, PopularMoviesUI> {
    override fun mapModel(model: PopularMoviesDomain): PopularMoviesUI {
        return with(model) {
            PopularMoviesUI(
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