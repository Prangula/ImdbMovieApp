package com.imdbmovieapp.data.local.mapper

import com.imdbmovieapp.data.local.entity.MovieEntity
import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.utils.ModelMapper

class MovieDomainToMovieEntityMapper : ModelMapper<MovieDomain, MovieEntity> {
    override fun mapModel(model: MovieDomain): MovieEntity {
        return with(model) {
            MovieEntity(
                id = id,
                title = title,
                year = year,
                genre = genre,
                image = image,
                isFavorite = isFavorite
            )
        }
    }
}