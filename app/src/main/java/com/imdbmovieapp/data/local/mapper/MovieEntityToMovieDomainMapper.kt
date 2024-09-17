package com.imdbmovieapp.data.local.mapper

import com.imdbmovieapp.data.local.entity.MovieEntity
import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.utils.ModelMapper

class MovieEntityToMovieDomainMapper : ModelMapper<MovieEntity, MovieDomain> {
    override fun mapModel(model: MovieEntity): MovieDomain {
        return with(model) {
            MovieDomain(
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