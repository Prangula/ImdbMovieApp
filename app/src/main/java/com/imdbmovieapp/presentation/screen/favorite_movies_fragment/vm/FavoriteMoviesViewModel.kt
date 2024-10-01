package com.imdbmovieapp.presentation.screen.favorite_movies_fragment.vm

import com.imdbmovieapp.domain.use_case.DeleteFavoriteMovieUseCase
import com.imdbmovieapp.domain.use_case.GenreMoviesUseCase
import com.imdbmovieapp.domain.use_case.GetFavoriteMoviesUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.GenreResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsUIToDomainMapper
import com.imdbmovieapp.presentation.model.GenreMoviesUI
import com.imdbmovieapp.presentation.model.GenreResultsUI
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.screen.favorite_movies_fragment.ui.FavoriteMoviesFragmentDirections
import com.imdbmovieapp.utils.lifecycle_scope_extensions.viewModelScope
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
        MutableStateFlow<Resource<List<GenreResultsUI>>>(Resource.Loading())
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
            FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToDetailMovieFragment(
                moviesResultsUI, genreMoviesUI
            )
        )
    }
}