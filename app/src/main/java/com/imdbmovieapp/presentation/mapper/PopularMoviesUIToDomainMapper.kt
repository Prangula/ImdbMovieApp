package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.presentation.model.PopularMoviesUI
import com.imdbmovieapp.utils.BaseMapper

class PopularMoviesUIToDomainMapper : BaseMapper<PopularMoviesUI, PopularMoviesDomain> {
    override fun mapModel(model: PopularMoviesUI): PopularMoviesDomain {
        return with(model) {
            PopularMoviesDomain(
                results = results,
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}