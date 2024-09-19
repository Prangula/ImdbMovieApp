package com.imdbmovieapp.domain.base

interface BaseUseCase<in DATA, out T> {
    abstract suspend operator fun invoke(data: DATA? = null): T
}