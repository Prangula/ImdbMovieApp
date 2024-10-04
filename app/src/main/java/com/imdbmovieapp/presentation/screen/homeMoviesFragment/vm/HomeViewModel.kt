package com.imdbmovieapp.presentation.screen.homeMoviesFragment.vm

import android.content.Context
import com.imdbmovieapp.data.remote.network.NetworkConnection
import com.imdbmovieapp.domain.useCase.DeleteFavoriteMovieUseCase
import com.imdbmovieapp.domain.useCase.GenreMoviesUseCase
import com.imdbmovieapp.domain.useCase.InsertFavoriteMovieUseCase
import com.imdbmovieapp.domain.useCase.PopularMoviesUseCase
import com.imdbmovieapp.domain.useCase.SearchMoviesUseCase
import com.imdbmovieapp.domain.useCase.TopRatedMoviesUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.GenreResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsUIToDomainMapper
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.movieStates.GenreState
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.movieStates.MovieState
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.ui.HomeMoviesFragmentDirections
import com.imdbmovieapp.utils.lifecycleScopeExtensions.viewModelScope
import com.imdbmovieapp.utils.movieConstants.MovieConstants.FAILED_TO_LOAD
import com.imdbmovieapp.utils.movieConstants.MovieConstants.NO_INTERNET_CONNECTION
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val genreMoviesUseCase: GenreMoviesUseCase,
    private val movieResultsDomainToUIMapper: MovieResultsDomainToUIMapper,
    private val movieResultsUIToDomainMapper: MovieResultsUIToDomainMapper,
    private val genreResultsDomainToUIMapper: GenreResultsDomainToUIMapper,
    private val insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val isNetworkConnection: NetworkConnection,
) : BaseViewModel() {

    private val _popularMovies = MutableStateFlow(MovieState())
    val popularMovie = _popularMovies.asStateFlow()

    private val _topRatedMovies =
        MutableStateFlow(MovieState())
    val topRatedMovies = _topRatedMovies.asStateFlow()

    private val _searchMovies =
        MutableStateFlow(MovieState())
    val searchMovies = _searchMovies.asStateFlow()

    private val _getGenres =
        MutableStateFlow(GenreState())
    val getGenres = _getGenres.asStateFlow()

    fun getPopularMovies(context: Context) {
        if (isNetworkConnection.isConnected()) {
            fetchMovies(
                useCase = { popularMoviesUseCase.invoke(Unit) },
                stateFlow = _popularMovies,
                context = context,
                resultsMapper = { data ->
                    data.results.map {
                        movieResultsDomainToUIMapper.mapModel(
                            it
                        )
                    }
                },
                showDialogOnClick = { showDialog(context) },
                hideDialogOnClick = { hideDialog() }
            )
        } else {
            errorToast(NO_INTERNET_CONNECTION, context)
            hideDialog()
        }
    }

    fun getTopRatedMovies(context: Context) {
        if (isNetworkConnection.isConnected()) {
            fetchMovies(
                useCase = { topRatedMoviesUseCase.invoke(Unit) },
                stateFlow = _topRatedMovies,
                context = context,
                resultsMapper = { data ->
                    data.results.map {
                        movieResultsDomainToUIMapper.mapModel(
                            it
                        )
                    }
                },
                showDialogOnClick = {},
                hideDialogOnClick = {}
            )
        } else {
            errorToast(NO_INTERNET_CONNECTION, context)
            hideDialog()
        }
    }

    fun getSearchMovies(query: String, context: Context) {
        if (isNetworkConnection.isConnected()) {
            fetchMovies(
                useCase = { searchMoviesUseCase.invoke(query) },
                stateFlow = _searchMovies,
                context = context,
                resultsMapper = { data ->
                    data.results.map {
                        movieResultsDomainToUIMapper.mapModel(
                            it
                        )
                    }
                },
                showDialogOnClick = {},
                hideDialogOnClick = {}
            )
        } else {
            errorToast(NO_INTERNET_CONNECTION, context)
            hideDialog()
        }
    }

    fun getGenreMovies(context: Context) {
        if (isNetworkConnection.isConnected()) {
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
        } else {
            errorToast(NO_INTERNET_CONNECTION, context)
            hideDialog()
        }
    }

    fun navigateToDetailsFragment(
        moviesResultsUI: MoviesResultsUI,
        genreMoviesUI: GenreMoviesUI,
    ) {
        navigateTo(
            HomeMoviesFragmentDirections.actionHomeMoviesFragmentToDetailMovieFragment(
                moviesResultsUI, genreMoviesUI
            )
        )
    }

    fun insert(moviesResultsUI: MoviesResultsUI) {
        viewModelScope {
            moviesResultsUI.isFavorite = true
            insertFavoriteMovieUseCase(movieResultsUIToDomainMapper.mapModel(moviesResultsUI))
        }
    }

    fun deleteMovie(moviesResultsUI: MoviesResultsUI) {
        viewModelScope {
            deleteFavoriteMovieUseCase(movieResultsUIToDomainMapper.mapModel(moviesResultsUI))
        }
    }

}
