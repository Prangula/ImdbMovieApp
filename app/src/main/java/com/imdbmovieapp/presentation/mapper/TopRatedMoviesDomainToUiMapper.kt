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
                        it.id,
                        it.genreIds,
                        it.posterPath,
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