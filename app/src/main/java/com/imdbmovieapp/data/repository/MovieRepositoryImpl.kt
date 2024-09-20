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

class MovieRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val popularDtoToPopularDomainMapper: PopularDtoToPopularDomainMapper,
    private val topRatedDtoToTopRatedDomainMapper: TopRatedDtoToTopRatedDomainMapper,
    private val detailDtoToDetailDomainMapper: DetailDtoToDetailDomainMapper,
    private val searchDtoToSearchDomainMapper: SearchDtoToSearchDomainMapper,
    private val genreDtoToDomainDto: GenreDtoToGenreDomainMapper
) : ApiMovieRepository {
    override suspend fun getPopularMovies(): List<PopularMoviesDomain> {
        val response = moviesApi.getPopularMovies()
        return if (response.isSuccessful) {
            popularDtoToPopularDomainMapper.mapToList(response.body()!!)
        } else {
            emptyList() //TODO
        }
    }

    override suspend fun getTopRatedMovies(): List<TopRatedMoviesDomain> {
        val response = moviesApi.getTopRatedMovies()
        return if (response.isSuccessful) {
            topRatedDtoToTopRatedDomainMapper.mapToList(response.body()!!)
        } else {
            emptyList()
        }
    }

    override suspend fun getDetailMovie(movieId: String): DetailMovieDomain {
        val response = moviesApi.getMovieDetails(movieId)
        return if (response.isSuccessful) {
            detailDtoToDetailDomainMapper.mapModel(response.body()!!)
        } else {
            throw Exception("Can not fetch details")
        }
    }

    override suspend fun getSearchMovies(query: String): List<SearchMoviesDomain> {
        val response = moviesApi.getSearchMovies(query)
        return if (response.isSuccessful) {
            searchDtoToSearchDomainMapper.mapToList(response.body()!!)
        } else {
            emptyList()
        }
    }

    override suspend fun getGenres(): List<GenreMoviesDomain> {
        val response = moviesApi.getMovieGenres()
        return if (response.isSuccessful) {
            genreDtoToDomainDto.mapToList(response.body()!!)
        } else {
            emptyList()
        }
    }
}