package com.imdbmovieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieTable")
data class MovieEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String? = null,
    val year: String? = null,
    val genre: String? = null,
    val image: String? = null,
    val isFavorite: Int? = null,
)