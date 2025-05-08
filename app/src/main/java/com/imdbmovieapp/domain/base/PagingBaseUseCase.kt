package com.imdbmovieapp.domain.base

import androidx.paging.PagingData
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface PagingBaseUseCase<in DATA, T : Any> {
    suspend operator fun invoke(data: DATA): Resource<Flow<PagingData<T>>>
}