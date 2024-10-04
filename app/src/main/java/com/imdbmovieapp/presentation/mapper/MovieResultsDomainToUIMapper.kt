package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.useCase.GetFavoriteMoviesUseCase
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.utils.base.BaseMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieResultsDomainToUIMapper(private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase) :
    BaseMapper<MoviesResultsDomain, MoviesResultsUI> {
    override fun mapModel(model: MoviesResultsDomain): MoviesResultsUI {
        return with(model) {
            MoviesResultsUI(
                id = id,
                genreIds = genreIds,
                posterPath = posterPath.orEmpty(),
                releaseDate = releaseDate,
                title = title,
                overview = overview,
                backdropPath = backdropPath.orEmpty(),
                voteAverage = voteAverage,
                isFavorite = isFavorite,
                heartColor = heartColor
            ).also { item ->
                //TODO
                GlobalScope.launch {
                    getFavoriteMoviesUseCase.invoke().collectLatest {
                        item.isFavorite = item.title in it.map { it.title }
                    }
                }
            }
        }
    }
}