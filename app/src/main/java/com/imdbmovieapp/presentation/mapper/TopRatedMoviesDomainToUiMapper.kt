package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.presentation.model.TopRatedMoviesUI
import com.imdbmovieapp.utils.BaseMapper

class TopRatedMoviesDomainToUiMapper : BaseMapper<TopRatedMoviesDomain, TopRatedMoviesUI> {
    override fun mapModel(model: TopRatedMoviesDomain): TopRatedMoviesUI {
        return with(model) {
            TopRatedMoviesUI(
                results = results,
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}