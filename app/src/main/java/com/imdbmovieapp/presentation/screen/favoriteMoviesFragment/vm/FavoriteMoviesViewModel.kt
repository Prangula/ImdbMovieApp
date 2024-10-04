package com.imdbmovieapp.presentation.screen.favoriteMoviesFragment.vm

import android.content.Context
import com.imdbmovieapp.domain.useCase.DeleteFavoriteMovieUseCase
import com.imdbmovieapp.domain.useCase.GenreMoviesUseCase
import com.imdbmovieapp.domain.useCase.GetFavoriteMoviesUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.GenreResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsUIToDomainMapper
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.movieStates.GenreState
import com.imdbmovieapp.presentation.screen.favoriteMoviesFragment.ui.FavoriteMoviesFragmentDirections
import com.imdbmovieapp.utils.lifecycleScopeExtensions.viewModelScope
import com.imdbmovieapp.utils.movieConstants.MovieConstants.FAILED_TO_LOAD
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class FavoriteMoviesViewModel(
    private val getFavoriteMoviesUseCaseImpl: GetFavoriteMoviesUseCase,
    private val movieResultsDomainToUIMapper: MovieResultsDomainToUIMapper,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val movieResultsUIToDomainMapper: MovieResultsUIToDomainMapper,
    private val genreResultsDomainToUIMapper: GenreResultsDomainToUIMapper,
    private val genreMoviesUseCase: GenreMoviesUseCase
) : BaseViewModel() {
    private val _favoriteMovies = MutableStateFlow<List<MoviesResultsUI>>(emptyList())
    val favoriteMovies = _favoriteMovies.asStateFlow()
    val emptyMovies: Flow<Boolean> = _favoriteMovies.map { it.isEmpty() }
    private val _getGenres =
        MutableStateFlow(GenreState())
    val getGenres = _getGenres.asStateFlow()


    fun getFavoriteMovies() {
        viewModelScope {
            getFavoriteMoviesUseCaseImpl().collect { movieList ->
                _favoriteMovies.value = movieResultsDomainToUIMapper.mapToList(movieList)
            }
        }
    }

    fun deleteMovie(moviesResultsUI: MoviesResultsUI) {
        viewModelScope {
            deleteFavoriteMovieUseCase(movieResultsUIToDomainMapper.mapModel(moviesResultsUI))
        }
    }

    fun getGenreMovies(context: Context) {
        viewModelScope {
            genreMoviesUseCase.invoke(Unit).collect { state ->
                when (state) {
                    is Resource.Error -> {
                        _getGenres.value =
                            GenreState(error = state.message!!)
                        errorToast(FAILED_TO_LOAD, context)
                    }

                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
                        _getGenres.value = GenreState(
                            genreList = state.data!!.genres.map {
                                genreResultsDomainToUIMapper.mapModel(
                                    it
                                )
                            })
                    }
                }
            }
        }
    }

    fun navigateToDetailsFragment(
        moviesResultsUI: MoviesResultsUI,
        genreMoviesUI: GenreMoviesUI,
    ) {
        navigateTo(
            FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToDetailMovieFragment(
                moviesResultsUI, genreMoviesUI
            )
        )
    }
}