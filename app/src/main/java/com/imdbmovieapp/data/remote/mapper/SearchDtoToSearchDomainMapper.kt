package com.imdbmovieapp.data.remote.mapper

import com.imdbmovieapp.data.remote.dto.SearchMoviesDto
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.utils.base.BaseMapper

class SearchDtoToSearchDomainMapper : BaseMapper<SearchMoviesDto, SearchMoviesDomain> {
    override fun mapModel(model: SearchMoviesDto): SearchMoviesDomain {
        return with(model) {
            SearchMoviesDomain(
                page = page,
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
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}