package com.imdbmovieapp.di

import com.imdbmovieapp.presentation.screen.detail_movie_fragment.vm.DetailViewModel
import com.imdbmovieapp.presentation.screen.home_movies_fragment.vm.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
}