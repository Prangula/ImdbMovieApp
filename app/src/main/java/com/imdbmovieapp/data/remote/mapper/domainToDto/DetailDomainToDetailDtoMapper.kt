package com.imdbmovieapp.data.remote.mapper.domainToDto

import com.imdbmovieapp.data.remote.dto.detailMovieDto.DetailMovieDto
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.utils.BaseMapper

class DetailDomainToDetailDtoMapper : BaseMapper<DetailMovieDomain, DetailMovieDto> {
    override fun mapModel(model: DetailMovieDomain): DetailMovieDto {
        return with(model) {
            DetailMovieDto(
                id = id,
                genreDtos = genreDtos,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage
            )
        }
    }
}