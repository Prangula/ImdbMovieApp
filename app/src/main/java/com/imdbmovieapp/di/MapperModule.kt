package com.imdbmovieapp.di

import com.imdbmovieapp.data.local.mapper.MovieDomainToMovieEntityMapper
import com.imdbmovieapp.data.local.mapper.MovieEntityToMovieDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.DetailDtoToDetailDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.GenreDtoToGenreDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.MovieResultsDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.SearchDtoToSearchDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.TopRatedDtoToTopRatedDomainMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.PopularMoviesDomainToUiMapper
import com.imdbmovieapp.presentation.mapper.PopularMoviesUIToDomainMapper
import com.imdbmovieapp.presentation.mapper.TopRatedMoviesDomainToUiMapper
import com.imdbmovieapp.presentation.mapper.TopRatedMoviesUIToDomainMapper
import org.koin.dsl.module

val mapperModule = module {
    single { MovieEntityToMovieDomainMapper() }
    single { MovieDomainToMovieEntityMapper() }
    single { TopRatedDtoToTopRatedDomainMapper() }
    single { DetailDtoToDetailDomainMapper() }
    single { SearchDtoToSearchDomainMapper() }
    single { GenreDtoToGenreDomainMapper() }
    single { PopularMoviesUIToDomainMapper() }
    single { PopularMoviesDomainToUiMapper() }
    single { TopRatedMoviesDomainToUiMapper() }
    single { TopRatedMoviesUIToDomainMapper() }
    single { MovieResultsDtoToDomainMapper() }
    single { MovieResultsDomainToUIMapper() }
}