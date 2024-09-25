package com.imdbmovieapp.presentation.screen.home_movies_fragment.vm

import com.imdbmovieapp.domain.use_case.PopularMoviesUseCase
import com.imdbmovieapp.domain.use_case.SearchMoviesUseCase
import com.imdbmovieapp.domain.use_case.TopRatedMoviesUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.MovieResultsDomainToUIMapper
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.utils.resource.Resource
import com.imdbmovieapp.utils.lifecycle_scope_extensions.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val movieResultsDomainToUIMapper: MovieResultsDomainToUIMapper,
) : BaseViewModel() {

    private val _popularMovies =
        MutableStateFlow<Resource<List<MoviesResultsUI>>>(Resource.Loading())
    val popularMovies = _popularMovies.asStateFlow()

    private val _topRatedMovies =
        MutableStateFlow<Resource<List<MoviesResultsUI>>>(Resource.Loading())
    val topRatedMovies = _topRatedMovies.asStateFlow()

    private val _searchMovies =
        MutableStateFlow<Resource<List<MoviesResultsUI>>>(Resource.Loading())
    val searchMovies = _searchMovies.asStateFlow()

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
            mapper = { movieResultsDomainToUIMapper.mapToList(it.results) },
            stateFlow = _popularMovies
        )
    }

    fun getTopRatedMovies() {
        getMovies(
            useCaseCall = { topRatedMoviesUseCase(Unit) },
            mapper = { movieResultsDomainToUIMapper.mapToList(it.results) },
            stateFlow = _topRatedMovies
        )
    }

    fun getSearchMovies(query: String) {
        if (query.isNotEmpty()) {
            getMovies(
                useCaseCall = { searchMoviesUseCase(query) },
                mapper = { movieResultsDomainToUIMapper.mapToList(it.results) },
                stateFlow = _searchMovies
            )
        }
    }
}
