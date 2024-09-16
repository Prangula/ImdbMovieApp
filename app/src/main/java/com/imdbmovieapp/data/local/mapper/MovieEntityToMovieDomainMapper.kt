package com.imdbmovieapp.data.local.mapper

import com.imdbmovieapp.data.local.entity.MovieEntity
import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.utils.UIMapper

class MovieEntityToMovieDomainMapper : UIMapper<MovieEntity, MovieDomain> {
    override fun mapModel(model: MovieEntity): MovieDomain {
        return with(model) {
            MovieDomain(
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