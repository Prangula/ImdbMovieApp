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
                        id = it.id,
                        genreIds = it.genreIds,
                        posterPath = it.posterPath,
                        releaseDate = it.releaseDate,
                        title = it.title,
                        overview = it.overview,
                        backdropPath = it.backdropPath,
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