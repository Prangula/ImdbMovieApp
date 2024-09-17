package com.imdbmovieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieTable")
data class FavoriteMovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val year: String,
    val genre: String,
    val image: String,
    val isFavorite: Int,
)