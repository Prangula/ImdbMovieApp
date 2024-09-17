package com.imdbmovieapp.data.repository

import com.imdbmovieapp.data.local.database.FavoriteMovieDatabase
import com.imdbmovieapp.data.local.mapper.MovieDomainToMovieEntityMapper
import com.imdbmovieapp.data.local.mapper.MovieEntityToMovieDomainMapper
import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl(
    private val favoriteMovieDatabase: FavoriteMovieDatabase,
    private val movieEntityToMovieDomainMapper: MovieEntityToMovieDomainMapper,
    private val movieDomainToMovieEntityMapper: MovieDomainToMovieEntityMapper
) : FavoriteMovieRepository {
    override suspend fun insert(favoriteMovieDomain: FavoriteMovieDomain) {
        return favoriteMovieDatabase.movieDao()
            .insertMovie(movieDomainToMovieEntityMapper.mapModel(favoriteMovieDomain))
    }

    override suspend fun delete(favoriteMovieDomain: FavoriteMovieDomain) {
        return favoriteMovieDatabase.movieDao()
            .deleteMovie(movieDomainToMovieEntityMapper.mapModel(favoriteMovieDomain))
    }

    override fun getAllMovies(): Flow<List<FavoriteMovieDomain>> {
        return favoriteMovieDatabase.movieDao().getAllMovies().map {
            movieEntityToMovieDomainMapper.mapToList(it)
        }
    }
}