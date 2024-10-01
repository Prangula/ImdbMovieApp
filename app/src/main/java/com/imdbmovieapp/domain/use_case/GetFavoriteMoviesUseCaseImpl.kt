package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCaseImpl(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : GetFavoriteMoviesUseCase {
    override  fun invoke(): Flow<List<MoviesResultsDomain>> {
        return favoriteMovieRepository.getAllMovies()
    }
}