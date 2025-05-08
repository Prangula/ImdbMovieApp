package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.resource.Resource

class SearchMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<String, SearchMoviesDomain> {
    override suspend fun invoke(data: String): Resource<SearchMoviesDomain> {
        return apiMovieRepository.getSearchMovies(data)
    }
}