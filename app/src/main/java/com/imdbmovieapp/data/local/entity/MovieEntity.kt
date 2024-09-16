package com.imdbmovieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieTable")
data class MovieEntity(
    val title: String = "",
    val year: String = "",
    val genre: String = "",
    val image: String = "",
    val isFavorite: Int = 0,
    @PrimaryKey
    val id: Int? = null,
)