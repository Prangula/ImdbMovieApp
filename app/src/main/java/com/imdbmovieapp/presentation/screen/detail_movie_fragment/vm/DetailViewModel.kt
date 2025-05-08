package com.imdbmovieapp.presentation.screen.detail_movie_fragment.vm

import com.imdbmovieapp.domain.use_case.DetailMovieUseCase
import com.imdbmovieapp.presentation.base.BaseViewModel
import com.imdbmovieapp.presentation.mapper.DetailDomainToUIMapper

class DetailViewModel(
    private val detailMoviesUseCase: DetailMovieUseCase,
    private val detailMovieDomainToUIMapper: DetailDomainToUIMapper
) : BaseViewModel() {
}