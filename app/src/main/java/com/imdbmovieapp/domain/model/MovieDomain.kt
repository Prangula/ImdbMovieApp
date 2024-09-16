package com.imdbmovieapp.domain.model

data class MovieDomain(
    val title: String = "",
    val year: String = "",
    val genre: String = "",
    val image: String = "",
    val isFavorite: Int = 0,
    val id: Int? = null
)
