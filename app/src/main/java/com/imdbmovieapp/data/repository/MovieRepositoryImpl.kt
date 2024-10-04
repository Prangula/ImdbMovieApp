package com.imdbmovieapp.data.repository

import com.imdbmovieapp.data.remote.api.MoviesApi
import com.imdbmovieapp.data.remote.mapper.DetailDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MovieGenreDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MoviesResponseDtoToDomainMapper
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource
import com.imdbmovieapp.domain.model.MoviesResponseDomain

class MovieRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val detailDtoToDomainMapper: DetailDtoToDomainMapper,
    private val movieGenreDtoToDomainMapper: MovieGenreDtoToDomainMapper,
    private val moviesResponseDtoToDomainMapper: MoviesResponseDtoToDomainMapper,
) : MovieRepository {
    override suspend fun getPopularMovies(): Resource<MoviesResponseDomain> {
        val response = moviesApi.getPopularMovies()
        return Resource.Success(moviesResponseDtoToDomainMapper.mapModel(response.body()!!))
    }

    override suspend fun getTopRatedMovies(): Resource<MoviesResponseDomain> {
        val response = moviesApi.getTopRatedMovies()
        return Resource.Success(moviesResponseDtoToDomainMapper.mapModel(response.body()!!))
    }

    override suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain> {
        val response = moviesApi.getMovieDetails(movieId)
        return Resource.Success(detailDtoToDomainMapper.mapModel(response.body()!!))
    }

    override suspend fun getSearchMovies(query: String): Resource<MoviesResponseDomain> {
        val response = moviesApi.getSearchMovies(query)
        return Resource.Success(moviesResponseDtoToDomainMapper.mapModel(response.body()!!))
    }

    override suspend fun getGenres(): Resource<GenreMoviesDomain> {
        val response = moviesApi.getMovieGenres()
        return Resource.Success(movieGenreDtoToDomainMapper.mapModel(response.body()!!))
    }
}