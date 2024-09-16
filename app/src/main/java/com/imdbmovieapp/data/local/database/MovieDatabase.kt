package com.imdbmovieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imdbmovieapp.data.local.dao.MovieDao
import com.imdbmovieapp.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}