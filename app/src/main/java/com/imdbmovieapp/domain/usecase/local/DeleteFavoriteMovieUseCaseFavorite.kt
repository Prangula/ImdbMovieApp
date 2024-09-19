package com.imdbmovieapp.domain.usecase.local

import com.imdbmovieapp.domain.base.FavoriteBaseUseCase
import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository

class DeleteFavoriteMovieUseCaseFavorite(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : FavoriteBaseUseCase<FavoriteMovieDomain, Unit> {
    override suspend fun invoke(data: FavoriteMovieDomain?) {
        favoriteMovieRepository.delete(data!!)
    }
}