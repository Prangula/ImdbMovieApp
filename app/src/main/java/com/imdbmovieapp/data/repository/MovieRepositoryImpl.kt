package com.imdbmovieapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.imdbmovieapp.data.local.database.MovieDatabase
import com.imdbmovieapp.data.local.mapper.MovieDomainToMovieEntityMapper
import com.imdbmovieapp.data.local.mapper.MovieEntityToMovieDomainMapper
import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieDatabase: MovieDatabase,
    private val movieEntityToMovieDomainMapper: MovieEntityToMovieDomainMapper,
    private val movieDomainToMovieEntityMapper: MovieDomainToMovieEntityMapper
) : MovieRepository {
    override suspend fun insert(movieDomain: MovieDomain) {
        return movieDatabase.movieDao().insert(movieDomainToMovieEntityMapper.mapModel(movieDomain))
    }

    override suspend fun delete(movieDomain: MovieDomain) {
        return movieDatabase.movieDao().delete(movieDomainToMovieEntityMapper.mapModel(movieDomain))
    }

    override fun getAllMovies(): LiveData<List<MovieDomain>> {
        return movieDatabase.movieDao().getAllMovies().map {
            movieEntityToMovieDomainMapper.mapToList(it)
        }
    }
}