package com.imdbmovieapp.domain.usecase

import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SearchMoviesUseCase(
    private val apiMovieRepository: ApiMovieRepository
) : BaseUseCase<String, SearchMoviesDomain> {
    override suspend fun invoke(data: String): Flow<Resource<List<SearchMoviesDomain>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val searchMovies = apiMovieRepository.getSearchMovies(data.toString())
                emit(Resource.Success(searchMovies))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "Http Error"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "No Internet Connection"))
            }
        }
    }
}