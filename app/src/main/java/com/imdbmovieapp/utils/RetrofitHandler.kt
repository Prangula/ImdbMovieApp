package com.imdbmovieapp.utils

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RetrofitHandler {
    inline fun <T, R> apiDataFetcher(
        apiResponse: () -> Response<T>,
        mapper: (T) -> R
    ): Resource<R> {
        return try {
            val response = apiResponse.invoke()
            if (response.isSuccessful) {
                Resource.Success(mapper(response.body()!!))
            } else {
                Resource.Error(response.errorBody()!!.string())
            }
        } catch (e: HttpException) {
            Resource.Error(e.message ?: "Http Error")
        } catch (e: IOException) {
            Resource.Error(e.message ?: "No Internet Connection")
        }
    }
}
