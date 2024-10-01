package com.imdbmovieapp.domain.repository

import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.model.MoviesResponseDomain
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.utils.resource.Resource

interface MovieRepository {
    suspend fun getPopularMovies(): Resource<MoviesResponseDomain>
    suspend fun getTopRatedMovies(): Resource<MoviesResponseDomain>
    suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain>
    suspend fun getSearchMovies(query: String): Resource<MoviesResponseDomain>
    suspend fun getGenres(): Resource<GenreMoviesDomain>
}