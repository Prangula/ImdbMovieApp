package com.imdbmovieapp.presentation.screen.homeMoviesFragment.movieStates

import com.imdbmovieapp.presentation.model.MoviesResultsUI

data class MovieState(
    val isLoading: Boolean = false,
    val movieList: List<MoviesResultsUI> = emptyList(),
    val error: String = ""
)
