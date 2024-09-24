package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.utils.BaseMapper

class MovieResultsDomainToUIMapper : BaseMapper<MoviesResultsDomain, MoviesResultsUI> {
    override fun mapModel(model: MoviesResultsDomain): MoviesResultsUI {
        return with(model) {
            MoviesResultsUI(
                id = id,
                genreIds = genreIds,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title
            )
        }
    }
}