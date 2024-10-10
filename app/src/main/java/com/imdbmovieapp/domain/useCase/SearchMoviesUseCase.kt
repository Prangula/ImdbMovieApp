package com.imdbmovieapp.domain.useCase

import android.os.Build
import androidx.annotation.RequiresExtension
import com.imdbmovieapp.data.remote.network.RetrofitHandler
import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<String, MoviesResponseDomain> {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun invoke(data: String): Flow<Resource<MoviesResponseDomain>> =
        RetrofitHandler().safeApiCall {
            movieRepository.getSearchMovies(data).data!!
        }
}