package com.imdbmovieapp.domain.useCase

import com.imdbmovieapp.domain.base.FavoriteBaseUseCase
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository

class InsertFavoriteMovieUseCase(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : FavoriteBaseUseCase<MoviesResultsDomain, Unit> {
    override suspend fun invoke(data: MoviesResultsDomain?) {
        favoriteMovieRepository.insert(data!!)
    }
}