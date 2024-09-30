package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource

class GenreMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<Unit, GenreMoviesDomain> {
    override suspend fun invoke(data: Unit): Resource<GenreMoviesDomain> {
        return movieRepository.getGenres()
    }
}