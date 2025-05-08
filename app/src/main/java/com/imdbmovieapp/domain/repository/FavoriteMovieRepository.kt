package com.imdbmovieapp.domain.repository

import com.imdbmovieapp.domain.model.MoviesResultsDomain
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {

    suspend fun insert(moviesResultsDomain: MoviesResultsDomain)

    suspend fun delete(moviesResultsDomain: MoviesResultsDomain)

    fun getAllMovies(): Flow<List<MoviesResultsDomain>>
}