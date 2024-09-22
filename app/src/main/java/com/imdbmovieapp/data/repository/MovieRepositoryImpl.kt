package com.imdbmovieapp.data.repository

import com.imdbmovieapp.data.remote.api.MoviesApi
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.DetailDtoToDetailDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.GenreDtoToGenreDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.PopularDtoToPopularDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.SearchDtoToSearchDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.TopRatedDtoToTopRatedDomainMapper
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource
import retrofit2.HttpException
import java.io.IOException

class MovieRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val detailDtoToDetailDomainMapper: DetailDtoToDetailDomainMapper,
    private val genreDtoToGenreDomainMapper: GenreDtoToGenreDomainMapper,
    private val popularDtoToPopularDomainMapper: PopularDtoToPopularDomainMapper,
    private val topRatedDtoToTopRatedDomainMapper: TopRatedDtoToTopRatedDomainMapper,
    private val searchDtoToSearchDomainMapper: SearchDtoToSearchDomainMapper,
) : ApiMovieRepository {
    override suspend fun getPopularMovies(): Resource<List<PopularMoviesDomain>> {
        val response = moviesApi.getPopularMovies()
        return try {
            if (response.isSuccessful) {
                Resource.Success(popularDtoToPopularDomainMapper.mapToList(response.body()!!))
            } else {
                Resource.Error("")
            }
        } catch (e: HttpException) {
            (Resource.Error(e.message ?: "Http Error"))
        } catch (e: IOException) {
            (Resource.Error(e.message ?: "No Internet Connection"))
        }
    }

    override suspend fun getTopRatedMovies(): Resource<List<TopRatedMoviesDomain>> {
        val response = moviesApi.getTopRatedMovies()
        return try {
            if (response.isSuccessful) {
                Resource.Success(topRatedDtoToTopRatedDomainMapper.mapToList(response.body()!!))
            } else {
                Resource.Error("")
            }
        } catch (e: HttpException) {
            (Resource.Error(e.message ?: "Http Error"))
        } catch (e: IOException) {
            (Resource.Error(e.message ?: "No Internet Connection"))
        }
    }

    override suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain> {
        val response = moviesApi.getMovieDetails(movieId)
        return try {
            if (response.isSuccessful) {
                Resource.Success(detailDtoToDetailDomainMapper.mapModel(response.body()!!))
            } else {
                Resource.Error("")
            }
        } catch (e: HttpException) {
            (Resource.Error(e.message ?: "Http Error"))
        } catch (e: IOException) {
            (Resource.Error(e.message ?: "No Internet Connection"))
        }
    }

    override suspend fun getSearchMovies(query: String): Resource<List<SearchMoviesDomain>> {
        val response = moviesApi.getSearchMovies(query)
        return try {
            if (response.isSuccessful) {
                Resource.Success(searchDtoToSearchDomainMapper.mapToList(response.body()!!))
            } else {
                Resource.Error("")
            }
        } catch (e: HttpException) {
            (Resource.Error(e.message ?: "Http Error"))
        } catch (e: IOException) {
            (Resource.Error(e.message ?: "No Internet Connection"))
        }
    }

    override suspend fun getGenres(): Resource<List<GenreMoviesDomain>> {
        val response = moviesApi.getMovieGenres()
        return try {
            if (response.isSuccessful) {
                Resource.Success(genreDtoToGenreDomainMapper.mapToList(response.body()!!))
            } else {
                Resource.Error("")
            }
        } catch (e: HttpException) {
            (Resource.Error(e.message ?: "Http Error"))
        } catch (e: IOException) {
            (Resource.Error(e.message ?: "No Internet Connection"))
        }
    }
}