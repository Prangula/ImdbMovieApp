package com.imdbmovieapp.data.remote.network_utils

import com.imdbmovieapp.utils.resource.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RetrofitHandler {
    inline fun <T, DOMAIN> apiDataFetcher(
        apiResponse: () -> Response<T>,
        success: (response: T) -> Resource.Success<DOMAIN>
    ): Resource<DOMAIN> {
        return try {
            val response = apiResponse.invoke()
            if (response.isSuccessful) {
                response.body()?.let(success) ?: Resource.Error(response.message())
            } else {
                Resource.Error(response.message())
            }
        } catch (e: HttpException) {
            Resource.Error(e.message ?: "Http Error")
        } catch (e: IOException) {
            Resource.Error(e.message ?: "Network Error")
        } catch (e: Exception) {
            Resource.Error("error")
        }
    }
}
