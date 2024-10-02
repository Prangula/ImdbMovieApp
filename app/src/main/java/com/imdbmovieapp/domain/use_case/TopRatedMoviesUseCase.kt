package com.imdbmovieapp.domain.use_case

import androidx.paging.PagingData
import com.imdbmovieapp.domain.base.PagingBaseUseCase
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

class TopRatedMoviesUseCase(
    private val movieRepository: MovieRepository
) : PagingBaseUseCase<Unit, MoviesResultsDomain> {
    override suspend operator fun invoke(data: Unit): Resource<Flow<PagingData<MoviesResultsDomain>>> {
        return movieRepository.getTopRatedMovies()
    }
}