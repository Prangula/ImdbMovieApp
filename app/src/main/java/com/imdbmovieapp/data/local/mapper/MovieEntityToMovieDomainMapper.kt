package com.imdbmovieapp.data.local.mapper

import com.imdbmovieapp.data.local.entity.FavoriteMovieEntity
import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.utils.BaseMapper

class MovieEntityToMovieDomainMapper : BaseMapper<FavoriteMovieEntity, FavoriteMovieDomain> {
    override fun mapModel(model: FavoriteMovieEntity): FavoriteMovieDomain {
        return with(model) {
            FavoriteMovieDomain(
                id = id,
                title = title,
                year = year,
                genre = genre,
                image = image,
                favoriteMovie = favoriteMovie
            )
        }
    }
}