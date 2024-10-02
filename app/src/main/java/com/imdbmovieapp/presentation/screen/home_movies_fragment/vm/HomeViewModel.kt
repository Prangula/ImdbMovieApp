package com.imdbmovieapp.presentation.screen.home_movies_fragment.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val genreMoviesUseCase: GenreMoviesUseCase,
    private val movieResultsDomainToUIMapper: MovieResultsDomainToUIMapper,
    private val movieResultsUIToDomainMapper: MovieResultsUIToDomainMapper,
    private val genreResultsDomainToUIMapper: GenreResultsDomainToUIMapper,
    private val insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase
) : BaseViewModel() {

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

    fun getPopularMovies() {
        viewModelScope {
            popularMoviesUseCase.invoke(Unit).data!!.collectLatest { pagingData ->
                _popularMovies.value =
                    Resource.Success(pagingData.map { movieResultsDomainToUIMapper.mapModel(it) })
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope {
            topRatedMoviesUseCase.invoke(Unit).data!!.collectLatest { pagingData ->
                _topRatedMovies.value =
                    Resource.Success(pagingData.map { movieResultsDomainToUIMapper.mapModel(it) })
            }
        }
    }

    fun getSearchMovies(query: String) {
        viewModelScope {
            searchMoviesUseCase.invoke(query).data!!.collectLatest { pagingData ->
                _searchMovies.value =
                    Resource.Success(pagingData.map { movieResultsDomainToUIMapper.mapModel(it) })
            }
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
