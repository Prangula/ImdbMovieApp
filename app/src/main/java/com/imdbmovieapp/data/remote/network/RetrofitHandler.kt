package com.imdbmovieapp.data.remote.network

import com.imdbmovieapp.utils.movieConstants.MovieConstants.ERROR
import com.imdbmovieapp.utils.movieConstants.MovieConstants.NETWORK_ERROR
import com.imdbmovieapp.utils.movieConstants.MovieConstants.UNEXPECTED_ERROR
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RetrofitHandler {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Flow<Resource<T>> = flow {
        try {
            emit(Resource.Loading())
            val result = apiCall()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(NETWORK_ERROR))
        } catch (e: Exception) {
            emit(Resource.Error(ERROR))
        }
    }
}