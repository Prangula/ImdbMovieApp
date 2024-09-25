package com.imdbmovieapp.di

import com.imdbmovieapp.domain.use_case.DeleteFavoriteMovieUseCase
import com.imdbmovieapp.domain.use_case.DetailMovieUseCase
import com.imdbmovieapp.domain.use_case.GenreMoviesUseCase
import com.imdbmovieapp.domain.use_case.GetFavoriteMoviesUseCase
import com.imdbmovieapp.domain.use_case.GetFavoriteMoviesUseCaseImpl
import com.imdbmovieapp.domain.use_case.InsertFavoriteMovieUseCase
import com.imdbmovieapp.domain.use_case.PopularMoviesUseCase
import com.imdbmovieapp.domain.use_case.SearchMoviesUseCase
import com.imdbmovieapp.domain.use_case.TopRatedMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<InsertFavoriteMovieUseCase> { InsertFavoriteMovieUseCase(get()) }
    single<DeleteFavoriteMovieUseCase> { DeleteFavoriteMovieUseCase(get()) }
    single<GetFavoriteMoviesUseCase> { GetFavoriteMoviesUseCaseImpl(get()) }
    single<DetailMovieUseCase> { DetailMovieUseCase(get()) }
    single<GenreMoviesUseCase> { GenreMoviesUseCase(get()) }
    single<PopularMoviesUseCase> { PopularMoviesUseCase(get()) }
    single<SearchMoviesUseCase> { SearchMoviesUseCase(get()) }
    single<TopRatedMoviesUseCase> { TopRatedMoviesUseCase(get()) }
}