package com.imdbmovieapp.domain.repository

import com.imdbmovieapp.domain.model.DetailMovieDomain
import com.imdbmovieapp.domain.model.GenreMoviesDomain
import com.imdbmovieapp.domain.model.MoviesResultsDomain
import com.imdbmovieapp.domain.model.SearchMoviesDomain
import com.imdbmovieapp.domain.model.TopRatedMoviesDomain
import com.imdbmovieapp.utils.Resource

interface ApiMovieRepository {
    suspend fun getPopularMovies(apiKey: String): Resource<List<MoviesResultsDomain>>
    suspend fun getTopRatedMovies(): Resource<List<TopRatedMoviesDomain>>
    suspend fun getDetailMovie(movieId: String): Resource<DetailMovieDomain>
    suspend fun getSearchMovies(query: String): Resource<List<SearchMoviesDomain>>
    suspend fun getGenres(): Resource<List<GenreMoviesDomain>>
}