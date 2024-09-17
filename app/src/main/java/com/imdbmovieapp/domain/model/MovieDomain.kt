package com.imdbmovieapp.domain.model

data class MovieDomain(
    val id: Int? = null,
    val title: String? = null,
    val year: String? = null,
    val genre: String? = null,
    val image: String? = null,
    val isFavorite: Int? = null,
)
