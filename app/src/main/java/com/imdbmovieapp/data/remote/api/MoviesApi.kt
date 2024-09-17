package com.imdbmovieapp.data.remote.api

import com.imdbmovieapp.data.remote.dto.detailMovieDto.MovieDetailDto
import com.imdbmovieapp.data.remote.dto.detailMovieDto.MovieGenreDto
import com.imdbmovieapp.data.remote.dto.popularMoviesDto.PopularMoviesDto
import com.imdbmovieapp.data.remote.dto.searchMoviesDto.SearchMoviesDto
import com.imdbmovieapp.data.remote.dto.topRatedMoviesDto.TopRatedMoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

interface MoviesApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): Response<List<PopularMoviesDto>>

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(): Response<List<TopRatedMoviesDto>>

    @GET("/3/movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movieId: Int): Response<MovieDetailDto>

    @GET("/3/search/movie")
    suspend fun getSearchMovies(@Query("query") query: String): Response<List<SearchMoviesDto>>

    @GET("/3/genre/movie/list")
    suspend fun getGenres(): Response<List<MovieGenreDto>>
}