package com.imdbmovieapp.domain.repository

import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {

    suspend fun insert(favoriteMovieDomain: FavoriteMovieDomain)

    suspend fun delete(favoriteMovieDomain: FavoriteMovieDomain)

    fun getAllMovies(): Flow<List<FavoriteMovieDomain>>
}