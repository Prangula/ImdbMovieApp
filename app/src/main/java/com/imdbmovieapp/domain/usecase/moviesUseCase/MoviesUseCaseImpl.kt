package com.imdbmovieapp.domain.usecase.moviesUseCase

import androidx.lifecycle.LiveData
import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.domain.repository.MovieRepository

class MoviesUseCaseImpl(
    private val movieRepository: MovieRepository
) : MoviesUseCase {
    override suspend fun invoke(): LiveData<List<MovieDomain>> {
        return movieRepository.getAllMovies()
    }
}