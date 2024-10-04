package com.imdbmovieapp.data.remote.networkUtils

//class RetrofitHandler {
//    suspend inline fun <T, DOMAIN> apiDataFetcher(
//        crossinline apiResponse: suspend () -> Response<T>, // API response function
//        crossinline success: (response: T) -> DOMAIN, // Success mapping function to DOMAIN
//        emit: (Resource<DOMAIN>) -> Unit // Emit function for state emissions
//    ) {
//        emit(Resource.Loading()) // Emit loading state
//        return try {
//            val response = apiResponse() // Call the API
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    emit(Resource.Success(success(it))) // Map response to DOMAIN and emit success
//                } ?: emit(Resource.Error("Empty response"))
//            } else {
//                emit(Resource.Error(response.message()))
//            }
//        } catch (e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
//        } catch (e: IOException) {
//            emit(Resource.Error("Network Error"))
//        } catch (e: Exception) {
//            emit(Resource.Error("An unexpected error occurred"))
//        }
//    }
//
//    fun <DOMAIN> emit(success: Resource.Success<DOMAIN>) {
//
//    }
//}