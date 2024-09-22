package com.imdbmovieapp.data.remote.mapper.dtoToDomain

import com.imdbmovieapp.data.remote.dto.topRatedMoviesDto.TopRatedMoviesDto
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.utils.BaseMapper

class TopRatedDtoToTopRatedDomainMapper : BaseMapper<TopRatedMoviesDto, TopRatedMoviesDomain> {
    override fun mapModel(model: TopRatedMoviesDto): TopRatedMoviesDomain {
        return with(model) {
            TopRatedMoviesDomain(
                results = results,
                page = page,
                totalPages = totalPages,
                totalResults = totalResults
            )
        }
    }
}