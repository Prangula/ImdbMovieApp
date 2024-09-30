package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource

class SearchMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<String, SearchMoviesDomain> {
    override suspend fun invoke(data: String): Resource<SearchMoviesDomain> {
        return movieRepository.getSearchMovies(data)
    }
}