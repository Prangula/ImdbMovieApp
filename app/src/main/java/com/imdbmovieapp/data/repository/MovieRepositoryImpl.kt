package com.imdbmovieapp.data.repository

import com.imdbmovieapp.data.remote.api.MoviesApi
import com.imdbmovieapp.data.remote.mapper.DetailDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MovieGenreDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MoviesResultsToDomainMapper
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource
import com.imdbmovieapp.data.remote.network_utils.RetrofitHandler
import com.imdbmovieapp.domain.model.MoviesResponseDomain

class MovieRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val detailDtoToDomainMapper: DetailDtoToDomainMapper,
    private val movieGenreDtoToDomainMapper: MovieGenreDtoToDomainMapper,
    private val moviesResultsToDomainMapper: MoviesResultsToDomainMapper,
) : MovieRepository {
    override suspend fun getPopularMovies(): Resource<MoviesResponseDomain> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getPopularMovies() }
        return Resource.Success(moviesResultsToDomainMapper.mapModel(response.data!!))
    }

    override suspend fun getTopRatedMovies(): Resource<MoviesResponseDomain> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getTopRatedMovies() }
        return Resource.Success(moviesResultsToDomainMapper.mapModel(response.data!!))
    }

    override suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getMovieDetails(movieId) }
        return Resource.Success(detailDtoToDomainMapper.mapModel(response.data!!))
    }

    override suspend fun getSearchMovies(query: String): Resource<MoviesResponseDomain> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getSearchMovies(query) }
        return Resource.Success(moviesResultsToDomainMapper.mapModel(response.data!!))
    }

    override suspend fun getGenres(): Resource<GenreMoviesDomain> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getMovieGenres() }
        return Resource.Success(movieGenreDtoToDomainMapper.mapModel(response.data!!))
    }
}