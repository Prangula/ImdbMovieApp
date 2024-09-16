package com.imdbmovieapp.data.local.mapper

import com.imdbmovieapp.data.local.entity.MovieEntity
import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.utils.UIMapper

class MovieDomainToMovieEntityMapper : UIMapper<MovieDomain, MovieEntity> {
    override fun mapModel(model: MovieDomain): MovieEntity {
        return with(model) {
            MovieEntity(
                title,
                year,
                genre,
                image,
                isFavorite,
                id
            )
        }
    }
}