package com.imdbmovieapp.presentation.screen.detail_movie_fragment.vm

import com.imdbmovieapp.domain.use_case.DeleteFavoriteMovieUseCase
import com.imdbmovieapp.domain.use_case.InsertFavoriteMovieUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.MovieResultsUIToDomainMapper
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.utils.lifecycle_scope_extensions.viewModelScope

class DetailViewModel(
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val movieResultsUIToDomainMapper: MovieResultsUIToDomainMapper,
    private val insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase,
) : BaseViewModel() {
    fun deleteMovie(moviesResultsUI: MoviesResultsUI) {
        viewModelScope {
            deleteFavoriteMovieUseCase(movieResultsUIToDomainMapper.mapModel(moviesResultsUI))
        }
    }
    fun insert(moviesResultsUI: MoviesResultsUI) {
        viewModelScope {
            insertFavoriteMovieUseCase(movieResultsUIToDomainMapper.mapModel(moviesResultsUI))
        }
    }
}