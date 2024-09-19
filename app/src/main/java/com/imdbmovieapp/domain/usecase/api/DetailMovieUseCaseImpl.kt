package com.imdbmovieapp.domain.usecase.api

import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DetailMovieUseCaseImpl(
    private val apiMovieRepository: ApiMovieRepository
) : DetailMovieUseCase {
    override suspend fun invoke(movieId: String): Flow<Resource<DetailMovieDomain>> {
        return flow {
            try {
                emit(Resource.Loading())
                val detailMovie = apiMovieRepository.getDetailMovie(movieId)
                emit(Resource.Success(detailMovie))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "Http Error"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "No Internet Connection"))
            }
        }
    }
}