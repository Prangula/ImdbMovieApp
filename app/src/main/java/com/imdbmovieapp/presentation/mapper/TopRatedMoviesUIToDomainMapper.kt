package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.presentation.model.TopRatedMoviesUI
import com.imdbmovieapp.utils.BaseMapper

class TopRatedMoviesUIToDomainMapper : BaseMapper<TopRatedMoviesUI, TopRatedMoviesDomain> {
    override fun mapModel(model: TopRatedMoviesUI): TopRatedMoviesDomain {
        return with(model) {
            TopRatedMoviesDomain(
                results = results,
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}