package com.imdbmovieapp.domain.repository

import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.model.PopularMoviesDomain
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain

interface ApiMovieRepository {
    suspend fun getPopularMovies(): List<PopularMoviesDomain>
    suspend fun getTopRatedMovies(): List<TopRatedMoviesDomain>
    suspend fun getDetailMovie(movieId: String): DetailMovieDomain
    suspend fun getSearchMovies(query: String): List<SearchMoviesDomain>
    suspend fun getGenres(): List<GenreMoviesDomain>
}