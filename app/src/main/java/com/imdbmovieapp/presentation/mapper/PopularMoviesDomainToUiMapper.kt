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
                        it.id,
                        it.genreIds,
                        it.posterPath.orEmpty(),
                        it.releaseDate,
                        it.title
                    )
                },
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}