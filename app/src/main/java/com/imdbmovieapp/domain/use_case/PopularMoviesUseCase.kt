package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource

class PopularMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<Unit, PopularMoviesDomain> {
    override suspend fun invoke(data: Unit): Resource<PopularMoviesDomain> {
        return movieRepository.getPopularMovies()
    }
}