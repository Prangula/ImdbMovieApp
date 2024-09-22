package com.imdbmovieapp.domain.usecase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource

class TopRatedMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<Unit, List<TopRatedMoviesDomain>> {
    override suspend fun invoke(data: Unit): Resource<List<TopRatedMoviesDomain>> {
        return apiMovieRepository.getTopRatedMovies()
    }
}