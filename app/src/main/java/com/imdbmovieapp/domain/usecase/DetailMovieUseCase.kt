package com.imdbmovieapp.domain.usecase

import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DetailMovieUseCase {
    suspend operator fun invoke(movieId: String): Flow<Resource<DetailMovieDomain>>
}