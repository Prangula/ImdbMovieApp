package com.imdbmovieapp.domain.usecase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource

class PopularMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<String, List<MoviesResultsDomain>> {
    override suspend fun invoke(apiKey: String): Resource<List<MoviesResultsDomain>> {
        return apiMovieRepository.getPopularMovies(apiKey)
    }
}