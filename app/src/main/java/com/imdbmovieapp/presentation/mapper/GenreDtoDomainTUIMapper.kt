package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.utils.base.BaseMapper

class GenreDtoDomainTUIMapper : BaseMapper<GenreMoviesDomain, GenreMoviesUI> {
    override fun mapModel(model: GenreMoviesDomain): GenreMoviesUI {
        return with(model) {
            GenreMoviesUI(
                id = id,
                name = name
            )
        }
    }
}