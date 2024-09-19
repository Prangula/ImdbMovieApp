package com.imdbmovieapp.data.local.mapper

import com.imdbmovieapp.data.local.entity.FavoriteMovieEntity
import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.utils.BaseMapper

class MovieDomainToMovieEntityMapper : BaseMapper<FavoriteMovieDomain, FavoriteMovieEntity> {
    override fun mapModel(model: FavoriteMovieDomain): FavoriteMovieEntity {
        with(model) {
            FavoriteMovieEntity(
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