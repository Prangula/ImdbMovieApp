package com.imdbmovieapp.domain.usecase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PopularMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<Unit, PopularMoviesDomain> {
    override suspend fun invoke(data: Unit): Flow<Resource<List<PopularMoviesDomain>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val popularMovies = apiMovieRepository.getPopularMovies()
                emit(Resource.Success(popularMovies))
            } catch (e: HttpException) {
                emit(Resource.Error("Http Error"))
            } catch (e: IOException) {
                emit(Resource.Error("No Internet Connection"))
            }
        }
    }
}