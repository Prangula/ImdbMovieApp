package com.imdbmovieapp.domain.usecase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource

class GenreMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<Unit, List<GenreMoviesDomain>> {
    override suspend fun invoke(data: Unit): Resource<List<GenreMoviesDomain>> {
        return apiMovieRepository.getGenres()
    }
}