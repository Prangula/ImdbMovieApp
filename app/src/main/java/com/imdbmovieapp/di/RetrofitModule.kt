package com.imdbmovieapp.di

import com.imdbmovieapp.data.remote.api.MoviesApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(MoviesApi::class.java)
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(Interceptor())
            .build()
    }
}

class Interceptor : Interceptor {
    private val apiKey = "8ebb26e68ca175bcc8629b4077769f82"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}

