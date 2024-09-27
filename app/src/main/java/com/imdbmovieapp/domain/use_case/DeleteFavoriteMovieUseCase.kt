package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.FavoriteBaseUseCase
import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository

class DeleteFavoriteMovieUseCase(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : FavoriteBaseUseCase<FavoriteMovieDomain, Unit> {
    override suspend fun invoke(data: FavoriteMovieDomain?) {
        favoriteMovieRepository.delete(data!!)
    }
}