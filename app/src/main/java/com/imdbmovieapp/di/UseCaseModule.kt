package com.imdbmovieapp.di

import com.imdbmovieapp.domain.usecase.DeleteFavoriteMovieUseCase
import com.imdbmovieapp.domain.usecase.DetailMovieUseCase
import com.imdbmovieapp.domain.usecase.GenreMoviesUseCase
import com.imdbmovieapp.domain.usecase.GetFavoriteMoviesUseCase
import com.imdbmovieapp.domain.usecase.GetFavoriteMoviesUseCaseImpl
import com.imdbmovieapp.domain.usecase.InsertFavoriteMovieUseCase
import com.imdbmovieapp.domain.usecase.PopularMoviesUseCase
import com.imdbmovieapp.domain.usecase.SearchMoviesUseCase
import com.imdbmovieapp.domain.usecase.TopRatedMoviesUseCase
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