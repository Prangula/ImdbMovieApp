package com.imdbmovieapp.data.repository

import com.imdbmovieapp.data.local.dao.FavoriteMovieDao
import com.imdbmovieapp.data.remote.dto.MoviesResultDto
import com.imdbmovieapp.data.remote.mapper.MovieResultsDomainToDtoMapper
import com.imdbmovieapp.data.remote.mapper.MovieResultsDtoToDomainMapper
import com.imdbmovieapp.domain.model.FavoriteMovieDomain
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl(
    private val favoriteMovieDao: FavoriteMovieDao,
    private val movieResultsDtoToDomainMapper: MovieResultsDtoToDomainMapper,
    private val movieResultsDomainToDtoMapper: MovieResultsDomainToDtoMapper
) : FavoriteMovieRepository {

    override suspend fun insert(moviesResultsDomain: MoviesResultsDomain) {
        favoriteMovieDao.insertMovie(
            movieResultsDomainToDtoMapper.mapModel(moviesResultsDomain)
        )
    }

    override suspend fun delete(moviesResultsDomain: MoviesResultsDomain) {
        favoriteMovieDao.deleteMovie(
            movieResultsDomainToDtoMapper.mapModel(moviesResultsDomain)
        )
    }

    override fun getAllMovies(): Flow<List<MoviesResultsDomain>> {
        return favoriteMovieDao.getAllMovies().map {
            movieResultsDtoToDomainMapper.mapToList(it)
        }
    }
}