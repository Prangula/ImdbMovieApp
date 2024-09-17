package com.imdbmovieapp.domain.usecase.getMoviesUseCase

import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCaseImpl(
    private val movieRepository: MovieRepository
) : GetMoviesUseCase {
    override suspend fun invoke(): Flow<List<MovieDomain>> {
        return movieRepository.getAllMovies()
    }
}