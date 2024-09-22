package com.imdbmovieapp.data.remote.mapper.dtoToDomain

import com.imdbmovieapp.data.remote.dto.popularMoviesDto.PopularMoviesDto
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class PopularDtoToPopularDomainMapper : BaseMapper<PopularMoviesDto, PopularMoviesDomain> {
    override fun mapModel(model: PopularMoviesDto): PopularMoviesDomain {
        return with(model) {
            PopularMoviesDomain(
                moviesResultDtos = moviesResultDtos,
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}