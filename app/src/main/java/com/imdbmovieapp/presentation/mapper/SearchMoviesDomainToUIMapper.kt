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