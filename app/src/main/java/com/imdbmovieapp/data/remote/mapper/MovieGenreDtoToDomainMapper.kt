package com.imdbmovieapp.data.remote.mapper

import com.imdbmovieapp.data.remote.dto.MovieGenreDto
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.model.GenreResultsDomain
import com.imdbmovieapp.utils.base.BaseMapper

class MovieGenreDtoToDomainMapper : BaseMapper<MovieGenreDto, GenreMoviesDomain> {
    override fun mapModel(model: MovieGenreDto): GenreMoviesDomain {
        return with(model) {
            GenreMoviesDomain(
                genres = genres.map {
                    GenreResultsDomain(
                        it.id,
                        it.name
                    )
                }
            )
        }
    }
}