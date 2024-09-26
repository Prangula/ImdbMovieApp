package com.imdbmovieapp.di

import com.imdbmovieapp.presentation.screen.home_movies_fragment.vm.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(),get(),get()) }
}