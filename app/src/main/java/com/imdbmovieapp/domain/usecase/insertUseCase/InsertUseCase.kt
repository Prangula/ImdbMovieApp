package com.imdbmovieapp.domain.usecase.insertUseCase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.domain.repository.MovieRepository

class InsertUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<MovieDomain, Unit> {
    override suspend fun invoke(data: MovieDomain?) {
        movieRepository.insert(data!!)
    }
}