package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.presentation.model.PopularMoviesUI
import com.imdbmovieapp.utils.BaseMapper

class PopularMoviesDomainToUiMapper : BaseMapper<PopularMoviesDomain, PopularMoviesUI> {
    override fun mapModel(model: PopularMoviesDomain): PopularMoviesUI {
        return with(model) {
            PopularMoviesUI(
                moviesResultDtos = moviesResultDtos,
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}