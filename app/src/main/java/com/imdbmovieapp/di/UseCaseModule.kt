package com.imdbmovieapp.di

import com.imdbmovieapp.domain.useCase.DeleteFavoriteMovieUseCase
import com.imdbmovieapp.domain.useCase.DetailMovieUseCase
import com.imdbmovieapp.domain.useCase.GenreMoviesUseCase
import com.imdbmovieapp.domain.useCase.GetFavoriteMoviesUseCase
import com.imdbmovieapp.domain.useCase.GetFavoriteMoviesUseCaseImpl
import com.imdbmovieapp.domain.useCase.InsertFavoriteMovieUseCase
import com.imdbmovieapp.domain.useCase.PopularMoviesUseCase
import com.imdbmovieapp.domain.useCase.SearchMoviesUseCase
import com.imdbmovieapp.domain.useCase.TopRatedMoviesUseCase
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