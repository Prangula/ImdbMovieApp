package com.imdbmovieapp.domain.base

import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in DATA, T> {
    suspend operator fun invoke(data: DATA): Flow<Resource<T>>
}