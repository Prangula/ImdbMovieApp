package com.imdbmovieapp.presentation.screen.homeMoviesFragment.movieStates

import com.imdbmovieapp.presentation.model.GenreResultsUI

data class GenreState(
    val isLoading: Boolean = false,
    val genreList: List<GenreResultsUI> = emptyList(),
    val error: String = ""
)
