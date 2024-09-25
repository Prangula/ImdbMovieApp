package com.imdbmovieapp.data.repository

import com.imdbmovieapp.data.remote.api.MoviesApi
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.DetailDtoToDetailDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.GenreDtoToGenreDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.MovieResultsDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.SearchDtoToSearchDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.TopRatedDtoToTopRatedDomainMapper
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.utils.Resource
import com.imdbmovieapp.data.remote.network_utils.RetrofitHandler

class MovieRepositoryImpl(
    private val moviesApi: MoviesApi,
    private val detailDtoToDetailDomainMapper: DetailDtoToDetailDomainMapper,
    private val genreDtoToGenreDomainMapper: GenreDtoToGenreDomainMapper,
    private val movieResultsDtoToDomainMapper: MovieResultsDtoToDomainMapper,
    private val topRatedDtoToTopRatedDomainMapper: TopRatedDtoToTopRatedDomainMapper,
    private val searchDtoToSearchDomainMapper: SearchDtoToSearchDomainMapper,
) : ApiMovieRepository {
    override suspend fun getPopularMovies(): Resource<List<MoviesResultsDomain>> {
        return RetrofitHandler().apiDataFetcher(
            { moviesApi.getPopularMovies() },
            { movieResultsDtoToDomainMapper.mapToList(it.results) }
        )
    }

    override suspend fun getTopRatedMovies(): Resource<List<TopRatedMoviesDomain>> {
        return RetrofitHandler().apiDataFetcher(
            { moviesApi.getTopRatedMovies() },
            { topRatedDtoToTopRatedDomainMapper.mapToList(it) }
        )
    }

    override suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain> {
        return RetrofitHandler().apiDataFetcher(
            { moviesApi.getMovieDetails(movieId) },
            { detailDtoToDetailDomainMapper.mapModel(it) }
        )
    }

    override suspend fun getSearchMovies(query: String): Resource<List<SearchMoviesDomain>> {
        return RetrofitHandler().apiDataFetcher(
            { moviesApi.getSearchMovies(query) },
            { searchDtoToSearchDomainMapper.mapToList(it) }
        )
    }

    override suspend fun getGenres(): Resource<List<GenreMoviesDomain>> {
        return RetrofitHandler().apiDataFetcher(
            { moviesApi.getMovieGenres() },
            { genreDtoToGenreDomainMapper.mapToList(it) }
        )
    }
}