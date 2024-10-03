package com.imdbmovieapp.presentation.screen.home_movies_fragment.vm

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.imdbmovieapp.data.remote.network.NetworkConnection
import com.imdbmovieapp.domain.use_case.DeleteFavoriteMovieUseCase
import com.imdbmovieapp.domain.use_case.GenreMoviesUseCase
import com.imdbmovieapp.domain.use_case.InsertFavoriteMovieUseCase
import com.imdbmovieapp.domain.use_case.PopularMoviesUseCase
import com.imdbmovieapp.domain.use_case.SearchMoviesUseCase
import com.imdbmovieapp.domain.use_case.TopRatedMoviesUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.GenreResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsUIToDomainMapper
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.GenreResultsUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.screen.home_movies_fragment.ui.HomeMoviesFragmentDirections
import com.imdbmovieapp.utils.lifecycle_scope_extensions.viewModelScope
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
    //TODO
    private val _popularMovies =
        MutableStateFlow<Resource<PagingData<MoviesResultsUI>>>(Resource.Loading())
    val popularMovies = _popularMovies.asStateFlow()

    private val _topRatedMovies =
        MutableStateFlow<Resource<PagingData<MoviesResultsUI>>>(Resource.Loading())
    val topRatedMovies = _topRatedMovies.asStateFlow()

    private val _searchMovies =
        MutableStateFlow<Resource<PagingData<MoviesResultsUI>>>(Resource.Loading())
    val searchMovies = _searchMovies.asStateFlow()

    private val _getGenres =
        MutableStateFlow<Resource<List<GenreResultsUI>>>(Resource.Loading())
    val getGenres = _getGenres.asStateFlow()

    fun getPopularMovies(context: Context) {
        if (isNetworkConnection.isConnected()) {
            viewModelScope {
                popularMoviesUseCase.invoke(Unit)
                    .data
                    ?.cachedIn(viewModelScope)
                    ?.collect { pagingData ->
                        _popularMovies.value = Resource.Success(pagingData.map { movieResultsDomainToUIMapper.mapModel(it) })
                        when (_popularMovies.value) {
                            is Resource.Loading -> {
                                _popularMovies.value = Resource.Loading()
                                showDialog(context)
                            }

                            is Resource.Success -> {
                                _popularMovies.value = Resource.Success(pagingData.map {
                                    movieResultsDomainToUIMapper.mapModel(it)
                                })
                                hideDialog()
                            }

                            is Resource.Error -> {
                                _popularMovies.value =
                                    Resource.Error(_popularMovies.value.message!!)
                                errorToast("Failed to load popular movies", context)
                                hideDialog()
                            }
                        }
                    }
            }
        } else {
            errorToast("No Internet Connection", context)
            hideDialog()
        }
    }


    fun getTopRatedMovies(context: Context) {
        if (isNetworkConnection.isConnected()) {
            _topRatedMovies.value = Resource.Loading()
            showDialog(context)
            viewModelScope {
                topRatedMoviesUseCase.invoke(Unit).data?.cachedIn(viewModelScope)
                    ?.collect { pagingData ->
                        _topRatedMovies.value = Resource.Success(
                            pagingData.map { movieResultsDomainToUIMapper.mapModel(it) }
                        )
                        hideDialog()
                    }
                _topRatedMovies.value = Resource.Error(_topRatedMovies.value.message!!)
            }
        } else {
            errorToast("No Internet Connection", context)
            hideDialog()
        }
    }

    fun getSearchMovies(query: String, context: Context) {
        if (isNetworkConnection.isConnected()) {
            showDialog(context)
            viewModelScope {
                searchMoviesUseCase.invoke(query).data?.cachedIn(viewModelScope)
                    ?.collect { pagingData ->
                        _searchMovies.value = Resource.Success(
                            pagingData.map { movieResultsDomainToUIMapper.mapModel(it) }
                        )
                    }
                _searchMovies.value = Resource.Error(_searchMovies.value.message!!)
            }
        } else {
            errorToast("No Internet Connection", context)
        }
    }

    fun getGenreMovies() {
        getMovies(
            useCaseCall = { genreMoviesUseCase(Unit) },
            mapper = { genreResultsDomainToUIMapper.mapToList(it.genres) },
            stateFlow = _getGenres
        )
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
