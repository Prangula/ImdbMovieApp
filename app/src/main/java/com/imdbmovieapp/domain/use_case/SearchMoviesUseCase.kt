package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource

class SearchMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<String, MoviesResponseDomain> {
    override suspend fun invoke(data: String): Resource<MoviesResponseDomain> {
        return movieRepository.getSearchMovies(data)
    }
}