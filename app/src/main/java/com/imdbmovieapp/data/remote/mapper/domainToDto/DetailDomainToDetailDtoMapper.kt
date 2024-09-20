package com.imdbmovieapp.data.remote.mapper.domainToDto

import com.imdbmovieapp.data.remote.dto.detailMovieDto.MovieDetailsDto
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.utils.BaseMapper

class DetailDomainToDetailDtoMapper : BaseMapper<DetailMovieDomain, MovieDetailsDto> {
    override fun mapModel(model: DetailMovieDomain): MovieDetailsDto {
        return with(model) {
            MovieDetailsDto(
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