package com.imdbmovieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imdbmovieapp.data.remote.dto.MoviesResultDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieResultsDto: MoviesResultDto)

    @Delete
    suspend fun deleteMovie(movieResultsDto: MoviesResultDto)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): Flow<List<MoviesResultDto>>
}