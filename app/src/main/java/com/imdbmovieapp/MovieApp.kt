package com.imdbmovieapp

import android.app.Application
import com.imdbmovieapp.di.databaseModule
import com.imdbmovieapp.di.mapperModule
import com.imdbmovieapp.di.repositoryModule
import com.imdbmovieapp.di.retrofitModule
import com.imdbmovieapp.di.useCaseModule
import com.imdbmovieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(
                listOf(
                    databaseModule, mapperModule, repositoryModule, useCaseModule, retrofitModule,
                    viewModelModule
                )
            )
        }
    }
}