package com.imdbmovieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imdbmovieapp.data.local.dao.FavoriteMovieDao
import com.imdbmovieapp.data.local.entity.FavoriteMovieEntity

@Database(
    entities = [FavoriteMovieEntity::class],
    version = 2
)
abstract class FavoriteMovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}