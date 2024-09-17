package com.imdbmovieapp.data.repository

import com.imdbmovieapp.data.local.database.MovieDatabase
import com.imdbmovieapp.data.local.mapper.MovieDomainToMovieEntityMapper
import com.imdbmovieapp.data.local.mapper.MovieEntityToMovieDomainMapper
import com.imdbmovieapp.domain.model.MovieDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val movieDatabase: MovieDatabase,
    private val movieEntityToMovieDomainMapper: MovieEntityToMovieDomainMapper,
    private val movieDomainToMovieEntityMapper: MovieDomainToMovieEntityMapper
) : MovieRepository {
    override suspend fun insert(movieDomain: MovieDomain) {
        return movieDatabase.movieDao()
            .insertMovie(movieDomainToMovieEntityMapper.mapModel(movieDomain))
    }

    override suspend fun delete(movieDomain: MovieDomain) {
        return movieDatabase.movieDao()
            .deleteMovie(movieDomainToMovieEntityMapper.mapModel(movieDomain))
    }

    override fun getAllMovies(): Flow<List<MovieDomain>> {
        return movieDatabase.movieDao().getAllMovies().map {
            movieEntityToMovieDomainMapper.mapToList(it)
        }
    }
}