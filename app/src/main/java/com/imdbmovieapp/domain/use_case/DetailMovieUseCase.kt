package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource

class DetailMovieUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<String, DetailMovieDomain> {
    override suspend fun invoke(data: String): Resource<DetailMovieDomain> {
        return movieRepository.getDetailMovie(data)
    }
}