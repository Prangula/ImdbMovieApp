package com.imdbmovieapp.domain.useCase

import com.imdbmovieapp.domain.model.MoviesResultsDomain
import kotlinx.coroutines.flow.Flow

interface GetFavoriteMoviesUseCase {
     operator fun invoke(): Flow<List<MoviesResultsDomain>>
}