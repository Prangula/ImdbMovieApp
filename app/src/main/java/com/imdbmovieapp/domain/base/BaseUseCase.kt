package com.imdbmovieapp.domain.base

interface BaseUseCase<in DATA, out T> {
    suspend operator fun invoke(data: DATA? = null): T
}