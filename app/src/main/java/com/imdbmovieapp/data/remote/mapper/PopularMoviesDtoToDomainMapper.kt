package com.imdbmovieapp.data.remote.mapper

import com.imdbmovieapp.data.remote.dto.PopularMoviesDto
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.utils.base.BaseMapper

class PopularMoviesDtoToDomainMapper : BaseMapper<PopularMoviesDto, PopularMoviesDomain> {
    override fun mapModel(model: PopularMoviesDto): PopularMoviesDomain {
        return with(model) {
            PopularMoviesDomain(
                results = results.map {
                    MoviesResultsDomain(
                        it.id,
                        it.genreIds,
                        it.posterPath,
                        it.releaseDate,
                        it.title,
                        it.overview,
                        it.backdropPath,
                        it.voteAverage
                    )
                },
                page,
                totalPages,
                totalResults
            )
        }
    }
}