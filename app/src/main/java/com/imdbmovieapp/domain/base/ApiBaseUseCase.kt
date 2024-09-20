package com.imdbmovieapp.domain.base

import com.imdbmovieapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ApiBaseUseCase<in DATA, T> {
    suspend operator fun invoke(data: DATA): Flow<Resource<T>>
}