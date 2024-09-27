package com.imdbmovieapp.domain.repository

import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.utils.resource.Resource

interface ApiMovieRepository {
    suspend fun getPopularMovies(): Resource<PopularMoviesDomain>
    suspend fun getTopRatedMovies(): Resource<TopRatedMoviesDomain>
    suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain>
    suspend fun getSearchMovies(query: String): Resource<SearchMoviesDomain>
    suspend fun getGenres(): Resource<GenreMoviesDomain>
}