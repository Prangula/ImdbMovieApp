package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.resource.Resource

class PopularMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<Unit, PopularMoviesDomain> {
    override suspend fun invoke(data: Unit): Resource<PopularMoviesDomain> {
        return apiMovieRepository.getPopularMovies()
    }
}