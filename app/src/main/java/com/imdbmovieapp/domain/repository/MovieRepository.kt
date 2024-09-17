package com.imdbmovieapp.domain.repository

import com.imdbmovieapp.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun insert(movieDomain: MovieDomain)

    suspend fun delete(movieDomain: MovieDomain)

    fun getAllMovies(): Flow<List<MovieDomain>>
}