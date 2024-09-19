package com.imdbmovieapp.domain.usecase.getMoviesUseCase

import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import kotlinx.coroutines.flow.Flow

interface GetFavoriteMoviesUseCase {
    suspend operator fun invoke(): Flow<List<FavoriteMovieDomain>>
}