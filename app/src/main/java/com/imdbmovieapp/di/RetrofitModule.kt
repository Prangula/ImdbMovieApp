package com.imdbmovieapp.di

import com.imdbmovieapp.data.remote.api.MoviesApi
import com.imdbmovieapp.data.remote.network_utils.NetworkKeys.API_KEY
import com.imdbmovieapp.data.remote.network_utils.NetworkKeys.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
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
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}

