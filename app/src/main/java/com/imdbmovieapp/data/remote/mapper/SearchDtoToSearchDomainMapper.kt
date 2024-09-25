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
                        it.id ?: 1,
                        it.genreIds ?: emptyList(),
                        it.posterPath ?: "R.drawable.ic_empty",
                        it.releaseDate ?: "xxxx",
                        it.title ?: "unknown"
                    )
                },
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}