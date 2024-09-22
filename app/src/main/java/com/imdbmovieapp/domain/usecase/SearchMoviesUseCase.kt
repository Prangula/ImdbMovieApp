package com.imdbmovieapp.domain.usecase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource

class SearchMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<String, List<SearchMoviesDomain>> {
    override suspend fun invoke(data: String): Resource<List<SearchMoviesDomain>> {
        return apiMovieRepository.getSearchMovies(data)
    }
}