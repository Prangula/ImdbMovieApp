package com.imdbmovieapp.domain.usecase.deleteMovieUseCase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository

class DeleteFavoriteMovieUseCase(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : BaseUseCase<FavoriteMovieDomain, Unit> {
    override suspend fun invoke(data: FavoriteMovieDomain?) {
        favoriteMovieRepository.delete(data!!)
    }
}