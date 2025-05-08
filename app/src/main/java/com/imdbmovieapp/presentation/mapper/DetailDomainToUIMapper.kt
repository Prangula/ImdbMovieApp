package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.presentation.model.DetailMovieUI
import com.imdbmovieapp.presentation.model.GenreResultsUI
import com.imdbmovieapp.utils.base.BaseMapper

class DetailDomainToUIMapper : BaseMapper<DetailMovieDomain, DetailMovieUI> {
    override fun mapModel(model: DetailMovieDomain): DetailMovieUI {
        return with(model) {
            DetailMovieUI(
                id = id,
                genreDtos = genreDtos.map {
                    GenreResultsUI(
                        it.id,
                        it.name
                    )
                },
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
            )
        }
    }
}