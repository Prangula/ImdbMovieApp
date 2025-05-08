package com.imdbmovieapp.data.remote.api

import com.imdbmovieapp.data.remote.dto.MovieDetailsDto
import com.imdbmovieapp.data.remote.dto.MovieGenreDto
import com.imdbmovieapp.data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

interface MoviesApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
    ): Response<MoviesResponseDto>

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = 1,
    ): Response<MoviesResponseDto>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): Response<MovieDetailsDto>

    @GET("/3/search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
    ): Response<MoviesResponseDto>

    @GET("/3/genre/movie/list")
    suspend fun getMovieGenres(@Query("language") language: String = "en-EN"): Response<MovieGenreDto>
}