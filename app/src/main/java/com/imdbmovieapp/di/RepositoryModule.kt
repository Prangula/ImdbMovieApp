package com.imdbmovieapp.di

import com.imdbmovieapp.data.repository.FavoriteMovieRepositoryImpl
import com.imdbmovieapp.data.repository.MovieRepositoryImpl
import com.imdbmovieapp.domain.repository.MovieRepository
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<FavoriteMovieRepository> { FavoriteMovieRepositoryImpl(get(), get(), get()) }
    single<MovieRepository> {
        MovieRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
        )
    }
}