package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.model.SearchMoviesUI
import com.imdbmovieapp.utils.base.BaseMapper

class SearchMoviesDomainToUIMapper : BaseMapper<SearchMoviesDomain, SearchMoviesUI> {
    override fun mapModel(model: SearchMoviesDomain): SearchMoviesUI {
        return with(model) {
            SearchMoviesUI(
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