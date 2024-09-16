package com.imdbmovieapp.domain.repository

import androidx.lifecycle.LiveData
import com.imdbmovieapp.domain.model.MovieDomain

interface MovieRepository {

    suspend fun insert(movieDomain: MovieDomain)

    suspend fun delete(movieDomain: MovieDomain)

    fun getAllMovies(): LiveData<List<MovieDomain>>
}