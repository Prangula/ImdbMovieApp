package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.GenreResultsUI
import com.imdbmovieapp.utils.base.BaseMapper

class MovieGenreDomainToUIMapper : BaseMapper<GenreMoviesDomain, GenreMoviesUI> {
    override fun mapModel(model: GenreMoviesDomain): GenreMoviesUI {
        return with(model) {
            GenreMoviesUI(
                genres = genres.map {
                    GenreResultsUI(
                        it.id,
                        it.name
                    )
                }
            )
        }
    }
}