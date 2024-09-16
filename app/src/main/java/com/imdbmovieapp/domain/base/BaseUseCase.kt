package com.imdbmovieapp.domain.base

interface BaseUseCase<in Data, out T> {
    abstract suspend operator fun invoke(data: Data? = null): T
}