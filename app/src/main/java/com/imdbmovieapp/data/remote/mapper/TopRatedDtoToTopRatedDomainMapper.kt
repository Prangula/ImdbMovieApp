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