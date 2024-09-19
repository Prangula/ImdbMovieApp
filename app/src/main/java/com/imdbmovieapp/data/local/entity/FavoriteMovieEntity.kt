package com.imdbmovieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class FavoriteMovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val year: String,
    val genre: String,
    val image: String,
    val favoriteMovie: Int,
)