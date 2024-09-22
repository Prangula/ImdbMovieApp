package com.imdbmovieapp.domain.base

import com.imdbmovieapp.utils.Resource

interface BaseUseCase<in DATA, T> {
    suspend operator fun invoke(data: DATA): Resource<T>
}