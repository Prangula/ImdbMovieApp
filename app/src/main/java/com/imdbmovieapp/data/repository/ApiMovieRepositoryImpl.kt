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

class ApiMovieRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val popularDtoToPopularDomainMapper: PopularDtoToPopularDomainMapper,
    private val topRatedDtoToTopRatedDomainMapper: TopRatedDtoToTopRatedDomainMapper,
    private val detailDtoToDetailDomainMapper: DetailDtoToDetailDomainMapper,
    private val searchDtoToSearchDomainMapper: SearchDtoToSearchDomainMapper,
    private val genreDtoToDomainDto: GenreDtoToGenreDomainMapper
) : ApiMovieRepository {
    override suspend fun getPopularMovies(): List<PopularMoviesDomain> {
        moviesApi.getPopularMovies().let { response ->
            return if (response.isSuccessful) {
                response.body()?.let {
                    popularDtoToPopularDomainMapper.mapToList(it)
                }!!
            } else {
                emptyList()
            }
        }
    }

    override suspend fun getTopRatedMovies(): List<TopRatedMoviesDomain> {
        moviesApi.getTopRatedMovies().let { response ->
            return if (response.isSuccessful) {
                response.body()?.let { topRatedDtoToTopRatedDomainMapper.mapToList(it) }!!
            } else {
                emptyList()
            }
        }
    }

    override suspend fun getDetailMovie(movieId: String): DetailMovieDomain {
        moviesApi.getDetailMovie(movieId).let { response ->
            return if (response.isSuccessful) {
                response.body()?.let { detailDtoToDetailDomainMapper.mapModel(it) }!!
            } else {
                throw Exception("Can not fetch details")
            }
        }
    }

    override suspend fun getSearchMovies(query: String): List<SearchMoviesDomain> {
        moviesApi.getSearchMovies(query).let { response ->
            return if (response.isSuccessful) {
                response.body()?.let { searchDtoToSearchDomainMapper.mapToList(it) }!!
            } else {
                emptyList()
            }
        }
    }

    override suspend fun getGenres(): List<GenreMoviesDomain> {
        moviesApi.getMovieGenres().let { response ->
            return if (response.isSuccessful) {
                response.body()?.let { genreDtoToDomainDto.mapToList(it) }!!
            } else {
                emptyList()
            }
        }
    }
}