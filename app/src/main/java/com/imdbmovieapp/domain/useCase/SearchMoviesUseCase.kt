package com.imdbmovieapp.domain.useCase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.movieConstants.MovieConstants.ERROR
import com.imdbmovieapp.utils.movieConstants.MovieConstants.NETWORK_ERROR
import com.imdbmovieapp.utils.movieConstants.MovieConstants.UNEXPECTED_ERROR
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class SearchMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<String, MoviesResponseDomain> {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun invoke(data: String): Flow<Resource<MoviesResponseDomain>> = flow {
            try {
                emit(Resource.Loading())
                val movies = movieRepository.getSearchMovies(query = data)
                emit(Resource.Success(movies.data!!))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: UNEXPECTED_ERROR))
            } catch (e: IOException) {
                emit(Resource.Error(NETWORK_ERROR))
            } catch (e: Exception) {
                emit(Resource.Error(ERROR))
            }
        }
}