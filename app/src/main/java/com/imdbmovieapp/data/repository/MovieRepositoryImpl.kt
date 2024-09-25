package com.imdbmovieapp.data.repository

import com.imdbmovieapp.data.remote.api.MoviesApi
import com.imdbmovieapp.data.remote.mapper.DetailDtoToDetailDomainMapper
import com.imdbmovieapp.data.remote.mapper.GenreDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.PopularMoviesDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.SearchDtoToSearchDomainMapper
import com.imdbmovieapp.data.remote.mapper.TopRatedDtoToTopRatedDomainMapper
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.resource.Resource
import com.imdbmovieapp.data.remote.network_utils.RetrofitHandler
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain

class MovieRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val detailDtoToDetailDomainMapper: DetailDtoToDetailDomainMapper,
    private val genreDtoToDomainMapper: GenreDtoToDomainMapper,
    private val popularMoviesDtoToDomainMapper: PopularMoviesDtoToDomainMapper,
    private val topRatedMoviesDtoToTopRatedDomainMapper: TopRatedDtoToTopRatedDomainMapper,
    private val searchMoviesDtoToDomainMapper: SearchDtoToSearchDomainMapper

) : ApiMovieRepository {
    override suspend fun getPopularMovies(): Resource<PopularMoviesDomain> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getPopularMovies() }
        return Resource.Success(popularMoviesDtoToDomainMapper.mapModel(response.data!!))
    }

    override suspend fun getTopRatedMovies(): Resource<TopRatedMoviesDomain> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getTopRatedMovies() }
        return Resource.Success(topRatedMoviesDtoToTopRatedDomainMapper.mapModel(response.data!!))
    }

    override suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getMovieDetails(movieId) }
        return Resource.Success(detailDtoToDetailDomainMapper.mapModel(response.data!!))
    }

    override suspend fun getSearchMovies(query: String): Resource<SearchMoviesDomain> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getSearchMovies(query) }
        return Resource.Success(searchMoviesDtoToDomainMapper.mapModel(response.data!!))
    }

    override suspend fun getGenres(): Resource<List<GenreMoviesDomain>> {
        val response = RetrofitHandler().apiDataFetcher { moviesApi.getMovieGenres() }
        return Resource.Success(genreDtoToDomainMapper.mapToList(response.data!!.genres))
    }
}