package com.imdbmovieapp.presentation.screen.homeMoviesFragment.vm

import com.imdbmovieapp.domain.usecase.PopularMoviesUseCase
import com.imdbmovieapp.domain.usecase.TopRatedMoviesUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.MovieResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.TopRatedMoviesDomainToUiMapper
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.model.TopRatedMoviesUI
import com.imdbmovieapp.utils.Resource
import com.imdbmovieapp.utils.lifeCycleScopeExtensions.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
    private val movieResultsDomainToUIMapper: MovieResultsDomainToUIMapper,
    private val topRatedDomainToUiMapper: TopRatedMoviesDomainToUiMapper
) : BaseViewModel() {

    private val _popularMovies =
        MutableStateFlow<Resource<List<MoviesResultsUI>>>(Resource.Loading())
    val popularMovies = _popularMovies.asStateFlow()

    private val _topRatedMovies =
        MutableStateFlow<Resource<List<TopRatedMoviesUI>>>(Resource.Loading())
    val topRatedMovies = _topRatedMovies.asStateFlow()

    private fun <T, R> getMovies(
        useCaseCall: suspend () -> Resource<T>,
        mapper: (T) -> R,
        stateFlow: MutableStateFlow<Resource<R>>
    ) {
        viewModelScope {
            val result = useCaseCall()
            stateFlow.value = when (result) {
                is Resource.Success -> Resource.Success(mapper(result.data!!))
                is Resource.Error -> Resource.Error(result.message!!)
                is Resource.Loading -> Resource.Loading()
            }
        }
    }

    fun getPopularMovies() {
        getMovies(
            useCaseCall = { popularMoviesUseCase(Unit) },
            mapper = { movieResultsDomainToUIMapper.mapToList(it) },
            stateFlow = _popularMovies
        )
    }

    private fun getTopRatedMovies() {
        getMovies(
            useCaseCall = { topRatedMoviesUseCase(Unit) },
            mapper = { topRatedDomainToUiMapper.mapToList(it) },
            stateFlow = _topRatedMovies
        )
    }
}
