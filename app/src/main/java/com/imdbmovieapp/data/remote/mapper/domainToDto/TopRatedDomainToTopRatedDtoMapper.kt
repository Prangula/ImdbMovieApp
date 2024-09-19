package com.imdbmovieapp.data.remote.mapper.domainToDto

import com.imdbmovieapp.data.remote.dto.topRatedMoviesDto.TopRatedMoviesDto
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class TopRatedDomainToTopRatedDtoMapper : BaseMapper<TopRatedMoviesDomain, TopRatedMoviesDto> {
    override fun mapModel(model: TopRatedMoviesDomain): TopRatedMoviesDto {
        return with(model) {
            TopRatedMoviesDto(
                results = results,
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}