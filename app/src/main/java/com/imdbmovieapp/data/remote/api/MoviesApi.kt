package com.imdbmovieapp.data.remote.api

import com.imdbmovieapp.data.remote.dto.MovieDetailsDto
import com.imdbmovieapp.data.remote.dto.MovieGenreDto
import com.imdbmovieapp.data.remote.dto.PopularMoviesDto
import com.imdbmovieapp.data.remote.dto.SearchMoviesDto
import com.imdbmovieapp.data.remote.dto.TopRatedMoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

interface MoviesApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US"
    ): Response<PopularMoviesDto>

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US"
    ): Response<TopRatedMoviesDto>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): Response<MovieDetailsDto>

    @GET("/3/search/movie")
    suspend fun getSearchMovies(@Query("query") query: String): Response<SearchMoviesDto>

    @GET("/3/genre/movie/list")
    suspend fun getMovieGenres(): Response<MovieGenreDto>
}