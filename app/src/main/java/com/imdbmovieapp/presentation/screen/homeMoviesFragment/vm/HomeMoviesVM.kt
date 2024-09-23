package com.imdbmovieapp.presentation.screen.homeMoviesFragment.vm

import com.imdbmovieapp.domain.usecase.PopularMoviesUseCase
import com.imdbmovieapp.domain.usecase.TopRatedMoviesUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.PopularMoviesDomainToUiMapper
import com.imdbmovieapp.presentation.mapper.PopularMoviesUIToDomainMapper
import com.imdbmovieapp.presentation.mapper.TopRatedMoviesDomainToUiMapper
import com.imdbmovieapp.presentation.model.PopularMoviesUI
import com.imdbmovieapp.presentation.model.TopRatedMoviesUI
import com.imdbmovieapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow

class HomeMoviesVM(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
    private val popularMoviesUIToDomainMapper: PopularMoviesUIToDomainMapper,
    private val popularMoviesDomainToUiMapper: PopularMoviesDomainToUiMapper,
    private val topRatedUIToDomainMapper: PopularMoviesUIToDomainMapper,
    private val topRatedDomainToUiMapper: TopRatedMoviesDomainToUiMapper
) : BaseViewModel() {

    private val _popularMovies =
        MutableStateFlow<Resource<List<PopularMoviesUI>>>(Resource.Loading())
    val popularMovies get() = _popularMovies
    private val _topRatedMovies =
        MutableStateFlow<Resource<List<TopRatedMoviesUI>>>(Resource.Loading())
    val topRatedMovies get() = _topRatedMovies

    fun getPopularMovies() {
        getMovies(
            { popularMoviesUseCase(Unit) },
            { popularMoviesDomainToUiMapper.mapModel(it) },
            _popularMovies
        )
    }
    fun getTopRatedMovies(){
        getMovies(
            { topRatedMoviesUseCase(Unit) },
            { topRatedDomainToUiMapper.mapModel(it) },
            _topRatedMovies
        )
    }
}