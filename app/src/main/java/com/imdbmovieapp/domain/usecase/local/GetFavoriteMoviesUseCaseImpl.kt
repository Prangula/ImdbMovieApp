package com.imdbmovieapp.domain.usecase.local

import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCaseImpl(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : GetFavoriteMoviesUseCase {
    override suspend fun invoke(): Flow<List<FavoriteMovieDomain>> {
        return favoriteMovieRepository.getAllMovies()
    }
}