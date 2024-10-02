package com.imdbmovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.imdbmovieapp.data.remote.api.MoviesApi
import com.imdbmovieapp.data.remote.mapper.DetailDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MovieGenreDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MovieResultsDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MoviesResponseDtoToDomainMapper
import com.imdbmovieapp.data.remote.movie_paging_source.MoviesPagingSource
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.utils.resource.Resource
import com.imdbmovieapp.data.remote.network_utils.RetrofitHandler
import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val detailDtoToDomainMapper: DetailDtoToDomainMapper,
    private val movieGenreDtoToDomainMapper: MovieGenreDtoToDomainMapper,
    private val moviesResultsToDomainMapper: MovieResultsDtoToDomainMapper,
    private val moviesResponseDtoToDomainMapper: MoviesResponseDtoToDomainMapper
) : MovieRepository {
    override suspend fun getPopularMovies(): Resource<Flow<PagingData<MoviesResultsDomain>>> {
        return RetrofitHandler().apiDataFetcher(
            apiResponse = { moviesApi.getPopularMovies() },
            success = {
                Resource.Success(
                    Pager(
                        config = PagingConfig(pageSize = 1, maxSize = 20),
                        pagingSourceFactory = { MoviesPagingSource(it.results) }
                    ).flow.map { pagingData ->
                        pagingData.map { moviesResultsToDomainMapper.mapModel(it) }
                    }
                )
            }
        )
    }

    override suspend fun getTopRatedMovies(): Resource<Flow<PagingData<MoviesResultsDomain>>> {
        return RetrofitHandler().apiDataFetcher(
            apiResponse = { moviesApi.getTopRatedMovies() },
            success = {
                Resource.Success(
                    Pager(
                        config = PagingConfig(pageSize = 1, maxSize = 20),
                        pagingSourceFactory = { MoviesPagingSource(it.results) }
                    ).flow.map { pagingData ->
                        pagingData.map { moviesResultsToDomainMapper.mapModel(it) }
                    }
                )
            }
        )
    }

    override suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain> {
        return RetrofitHandler().apiDataFetcher(
            apiResponse = { moviesApi.getMovieDetails(movieId) },
            success = {
                Resource.Success(detailDtoToDomainMapper.mapModel(it))
            }
        )
    }

    override suspend fun getSearchMovies(query: String): Resource<Flow<PagingData<MoviesResultsDomain>>> {
        return RetrofitHandler().apiDataFetcher(
            apiResponse = { moviesApi.getSearchMovies(query) },
            success = {
                Resource.Success(
                    Pager(
                        config = PagingConfig(pageSize = 1, maxSize = 20),
                        pagingSourceFactory = { MoviesPagingSource(it.results) }
                    ).flow.map { pagingData ->
                        pagingData.map { moviesResultsToDomainMapper.mapModel(it) }
                    }
                )
            }
        )
    }

    override suspend fun getGenres(): Resource<GenreMoviesDomain> {
        return RetrofitHandler().apiDataFetcher(
            apiResponse = { moviesApi.getMovieGenres() },
            success = {
                Resource.Success(movieGenreDtoToDomainMapper.mapModel(it))
            }
        )
    }
}