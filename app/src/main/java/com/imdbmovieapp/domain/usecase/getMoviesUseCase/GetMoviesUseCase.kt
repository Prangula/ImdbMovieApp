package com.imdbmovieapp.domain.usecase.getMoviesUseCase

import com.imdbmovieapp.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend operator fun invoke(): Flow<List<MovieDomain>>
}