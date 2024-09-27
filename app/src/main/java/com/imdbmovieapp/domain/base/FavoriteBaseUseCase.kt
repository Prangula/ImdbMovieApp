package com.imdbmovieapp.domain.base

interface FavoriteBaseUseCase<in DATA, out T> {
    suspend operator fun invoke(data: DATA? = null): T
}