package com.imdbmovieapp.di

import com.imdbmovieapp.data.local.mapper.MovieDomainToMovieEntityMapper
import com.imdbmovieapp.data.local.mapper.MovieEntityToMovieDomainMapper
import com.imdbmovieapp.data.remote.mapper.DetailDtoToDetailDomainMapper
import com.imdbmovieapp.data.remote.mapper.MovieGenreDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MovieResultsDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.PopularMoviesDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.SearchDtoToSearchDomainMapper
import com.imdbmovieapp.data.remote.mapper.TopRatedDtoToTopRatedDomainMapper
import com.imdbmovieapp.presentation.mapper.GenreDtoDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.GenreResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieGenreDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.PopularMoviesDomainToUiMapper
import com.imdbmovieapp.presentation.mapper.TopRatedMoviesDomainToUIMapper
import org.koin.dsl.module

val mapperModule = module {
    single { MovieEntityToMovieDomainMapper() }
    single { MovieDomainToMovieEntityMapper() }
    single { TopRatedDtoToTopRatedDomainMapper() }
    single { DetailDtoToDetailDomainMapper() }
    single { SearchDtoToSearchDomainMapper() }
    single { MovieGenreDtoToDomainMapper() }
    single { PopularMoviesDomainToUiMapper() }
    single { PopularMoviesDtoToDomainMapper() }
    single { TopRatedMoviesDomainToUIMapper() }
    single { MovieResultsDtoToDomainMapper() }
    single { MovieResultsDomainToUIMapper() }
    single { GenreDtoDomainToUIMapper() }
    single { GenreResultsDomainToUIMapper() }
    single { MovieGenreDomainToUIMapper() }

}