package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.FavoriteBaseUseCase
import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository

class InsertFavoriteMovieUseCase(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : FavoriteBaseUseCase<FavoriteMovieDomain, Unit> {
    override suspend fun invoke(data: FavoriteMovieDomain?) {
        favoriteMovieRepository.insert(data!!)
    }
}