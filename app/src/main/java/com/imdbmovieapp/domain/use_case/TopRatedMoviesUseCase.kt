package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.resource.Resource

class TopRatedMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<Unit, TopRatedMoviesDomain> {
    override suspend fun invoke(data: Unit): Resource<TopRatedMoviesDomain> {
        return apiMovieRepository.getTopRatedMovies()
    }
}