package com.imdbmovieapp.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imdbmovieapp.data.local.dao.FavoriteMovieDao
import com.imdbmovieapp.data.local.typeconverter.GenreTypeConverters
import com.imdbmovieapp.data.remote.dto.MoviesResultDto

@Database(
    entities = [MoviesResultDto::class],
    version = 667
)
@TypeConverters(GenreTypeConverters::class)
abstract class FavoriteMovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}