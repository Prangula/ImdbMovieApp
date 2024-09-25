package com.imdbmovieapp.data.remote.mapper

import com.imdbmovieapp.data.remote.dto.TopRatedMoviesDto
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.utils.base.BaseMapper

class TopRatedDtoToTopRatedDomainMapper : BaseMapper<TopRatedMoviesDto, TopRatedMoviesDomain> {
    override fun mapModel(model: TopRatedMoviesDto): TopRatedMoviesDomain {
        return with(model) {
            TopRatedMoviesDomain(
                results = results.map {
                    MoviesResultsDomain(
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