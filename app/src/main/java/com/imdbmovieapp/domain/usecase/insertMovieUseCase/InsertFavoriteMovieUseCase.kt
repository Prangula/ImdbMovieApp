package com.imdbmovieapp.domain.usecase.insertMovieUseCase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository

class InsertFavoriteMovieUseCase(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : BaseUseCase<FavoriteMovieDomain, Unit> {
    override suspend fun invoke(data: FavoriteMovieDomain?) {
        favoriteMovieRepository.insert(data!!)
    }
}