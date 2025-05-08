package com.imdbmovieapp.domain.useCase

import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource

class DetailMovieUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun invoke(data: String): Resource<DetailMovieDomain> {
        return movieRepository.getDetailMovie(data)
    }
}