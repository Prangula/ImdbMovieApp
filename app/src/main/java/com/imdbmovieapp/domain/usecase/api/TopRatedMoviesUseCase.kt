package com.imdbmovieapp.domain.usecase.api

import com.imdbmovieapp.domain.base.ApiBaseUseCase
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TopRatedMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : ApiBaseUseCase<Unit, TopRatedMoviesDomain> {
    override suspend fun invoke(data: Unit): Flow<Resource<List<TopRatedMoviesDomain>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val topRatedMovies = apiMovieRepository.getTopRatedMovies()
                emit(Resource.Success(topRatedMovies))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "Http Error"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "No Internet Connection"))
            }
        }
    }
}