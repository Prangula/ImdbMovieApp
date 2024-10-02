package com.imdbmovieapp.domain.repository

import androidx.paging.PagingData
import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Resource<Flow<PagingData<MoviesResultsDomain>>>
    suspend fun getTopRatedMovies(): Resource<Flow<PagingData<MoviesResultsDomain>>>
    suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain>
    suspend fun getSearchMovies(query: String): Resource<Flow<PagingData<MoviesResultsDomain>>>
    suspend fun getGenres(): Resource<GenreMoviesDomain>
}