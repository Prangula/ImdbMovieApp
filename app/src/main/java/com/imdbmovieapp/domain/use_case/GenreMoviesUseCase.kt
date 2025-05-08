package com.imdbmovieapp.domain.use_case

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.resource.Resource

class GenreMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<Unit, GenreMoviesDomain> {
    override suspend fun invoke(data: Unit): Resource<GenreMoviesDomain> {
        return apiMovieRepository.getGenres()
    }
}