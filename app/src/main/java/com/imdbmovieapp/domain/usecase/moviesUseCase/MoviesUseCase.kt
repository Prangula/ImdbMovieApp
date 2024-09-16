package com.imdbmovieapp.domain.usecase.moviesUseCase

import androidx.lifecycle.LiveData
import com.imdbmovieapp.domain.model.MovieDomain

interface MoviesUseCase {
    suspend operator fun invoke(): LiveData<List<MovieDomain>>
}