package com.imdbmovieapp.presentation.screen.detailMovieFragment.vm

import com.imdbmovieapp.domain.useCase.DeleteFavoriteMovieUseCase
import com.imdbmovieapp.domain.useCase.InsertFavoriteMovieUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.MovieResultsUIToDomainMapper
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.utils.lifecycleScopeExtensions.viewModelScope

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