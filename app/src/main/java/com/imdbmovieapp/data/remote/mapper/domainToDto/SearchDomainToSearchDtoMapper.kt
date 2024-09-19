package com.imdbmovieapp.data.remote.mapper.domainToDto

import com.imdbmovieapp.data.remote.dto.searchMoviesDto.SearchMoviesDto
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class SearchDomainToSearchDtoMapper : BaseMapper<SearchMoviesDomain, SearchMoviesDto> {
    override fun mapModel(model: SearchMoviesDomain): SearchMoviesDto {
        return with(model) {
            SearchMoviesDto(
                page = page,
                results = results,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}