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

class PopularMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<Unit, MoviesResponseDomain> {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend operator fun invoke(data: Unit): Flow<Resource<MoviesResponseDomain>> = flow {
        try {
            emit(Resource.Loading())
            val movies = movieRepository.getPopularMovies()
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