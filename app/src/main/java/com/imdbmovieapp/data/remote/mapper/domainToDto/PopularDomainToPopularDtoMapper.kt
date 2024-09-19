package com.imdbmovieapp.data.remote.mapper.domainToDto

import com.imdbmovieapp.data.remote.dto.popularMoviesDto.PopularMoviesDto
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class PopularDomainToPopularDtoMapper : BaseMapper<PopularMoviesDomain, PopularMoviesDto> {
    override fun mapModel(model: PopularMoviesDomain): PopularMoviesDto {
        return with(model) {
            PopularMoviesDto(
                moviesResultDtos = moviesResultDtos,
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}