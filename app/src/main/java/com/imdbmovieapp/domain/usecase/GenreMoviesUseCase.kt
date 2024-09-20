package com.imdbmovieapp.domain.usecase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GenreMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<Unit, GenreMoviesDomain> {
    override suspend fun invoke(data: Unit): Flow<Resource<List<GenreMoviesDomain>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val genreMovie = apiMovieRepository.getGenres()
                emit(Resource.Success(genreMovie))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "Http Error"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "No Internet Connection"))
            }
        }
    }
}