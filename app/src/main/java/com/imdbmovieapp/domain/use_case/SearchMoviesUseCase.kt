package com.imdbmovieapp.domain.use_case

import androidx.paging.PagingData
import com.imdbmovieapp.domain.base.PagingBaseUseCase
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(
    private val movieRepository: MovieRepository
) : PagingBaseUseCase<String, MoviesResultsDomain> {
    override suspend fun invoke(data: String): Resource<Flow<PagingData<MoviesResultsDomain>>> {
        return movieRepository.getSearchMovies(data)
    }
}