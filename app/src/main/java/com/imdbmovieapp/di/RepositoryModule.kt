package com.imdbmovieapp.di

import com.imdbmovieapp.data.repository.FavoriteMovieRepositoryImpl
import com.imdbmovieapp.data.repository.MovieRepositoryImpl
import com.imdbmovieapp.domain.repository.ApiMovieRepository
import com.imdbmovieapp.domain.repository.FavoriteMovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<FavoriteMovieRepository> { FavoriteMovieRepositoryImpl(get(), get(), get()) }
    single<ApiMovieRepository> {
        MovieRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }
}