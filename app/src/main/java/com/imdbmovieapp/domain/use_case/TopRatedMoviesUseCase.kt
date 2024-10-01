package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource

class TopRatedMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<Unit, MoviesResponseDomain> {
    override suspend fun invoke(data: Unit): Resource<MoviesResponseDomain> {
        return movieRepository.getTopRatedMovies()
    }
}