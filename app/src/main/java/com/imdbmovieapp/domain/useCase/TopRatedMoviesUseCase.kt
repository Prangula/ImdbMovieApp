package com.imdbmovieapp.domain.useCase

import android.os.Build
import androidx.annotation.RequiresExtension
import com.imdbmovieapp.data.remote.network.RetrofitHandler
import com.imdbmovieapp.domain.base.BaseUseCase
import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

class TopRatedMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<Unit, MoviesResponseDomain> {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend operator fun invoke(data: Unit): Flow<Resource<MoviesResponseDomain>> =
        RetrofitHandler().safeApiCall {
            movieRepository.getTopRatedMovies().data!!
        }
}