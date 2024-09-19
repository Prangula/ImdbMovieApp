package com.imdbmovieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imdbmovieapp.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Delete
    suspend fun deleteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): Flow<List<FavoriteMovieEntity>>
}