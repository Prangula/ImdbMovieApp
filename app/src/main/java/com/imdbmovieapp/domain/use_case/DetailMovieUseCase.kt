package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.resource.Resource

class DetailMovieUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<String, DetailMovieDomain> {
    override suspend fun invoke(data: String): Resource<DetailMovieDomain> {
        return apiMovieRepository.getDetailMovie(data)
    }
}