package com.imdbmovieapp.data.remote.network_utils

import com.imdbmovieapp.utils.resource.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RetrofitHandler {
    inline fun <T> apiDataFetcher(
        apiResponse: () -> Response<T>
    ): Resource<T> {
        return try {
            val response = apiResponse.invoke()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: HttpException) {
            Resource.Error(e.message ?: "Http Error")
        } catch (e: IOException) {
            Resource.Error(e.message ?: "No Internet Connection")
        }
    }
}
