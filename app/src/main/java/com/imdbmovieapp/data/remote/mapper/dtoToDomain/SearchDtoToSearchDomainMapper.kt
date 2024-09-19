package com.imdbmovieapp.data.remote.mapper.dtoToDomain

import com.imdbmovieapp.data.remote.dto.searchMoviesDto.SearchMoviesDto
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class SearchDtoToSearchDomainMapper : BaseMapper<SearchMoviesDto, SearchMoviesDomain> {
    override fun mapModel(model: SearchMoviesDto): SearchMoviesDomain {
        return with(model) {
            SearchMoviesDomain(
                page = page,
                results = results,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}