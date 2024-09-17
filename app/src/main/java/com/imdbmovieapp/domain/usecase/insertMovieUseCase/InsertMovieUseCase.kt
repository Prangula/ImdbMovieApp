package com.imdbmovieapp.domain.usecase.insertMovieUseCase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.domain.repository.MovieRepository

class InsertMovieUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<MovieDomain, Unit> {
    override suspend fun invoke(data: MovieDomain?) {
        movieRepository.insert(data!!)
    }
}