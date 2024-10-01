package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.model.TopRatedMoviesUI
import com.imdbmovieapp.utils.base.BaseMapper

class TopRatedMoviesDomainToUIMapper : BaseMapper<TopRatedMoviesDomain, TopRatedMoviesUI> {
    override fun mapModel(model: TopRatedMoviesDomain): TopRatedMoviesUI {
        return with(model) {
            TopRatedMoviesUI(
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