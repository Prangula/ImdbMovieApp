package com.imdbmovieapp.di

import android.content.Context
import androidx.room.Room
import com.imdbmovieapp.data.local.dao.FavoriteMovieDao
import com.imdbmovieapp.data.local.database.FavoriteMovieDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get<Context>(),
            FavoriteMovieDatabase::class.java,
            "movieTable"
        ).build()
    }
    single<FavoriteMovieDao> { get<FavoriteMovieDatabase>().favoriteMovieDao() }
}