package com.imdbmovieapp.di

import com.imdbmovieapp.data.local.mapper.FavoriteDomainToEntityMapper
import com.imdbmovieapp.data.local.mapper.FavoriteEntityToDomainMapper
import com.imdbmovieapp.data.remote.mapper.DetailDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MovieGenreDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MovieResultsDomainToDtoMapper
import com.imdbmovieapp.data.remote.mapper.MovieResultsDtoToDomainMapper
import com.imdbmovieapp.data.remote.mapper.MoviesResponseDtoToDomainMapper
import com.imdbmovieapp.presentation.mapper.DetailDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.GenreDtoDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.GenreResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieGenreDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsDomainToUIMapper
import com.imdbmovieapp.presentation.mapper.MovieResultsUIToDomainMapper
import com.imdbmovieapp.presentation.mapper.MoviesResponseDomainToUi
import org.koin.dsl.module

val mapperModule = module {
    single { FavoriteEntityToDomainMapper() }
    single { FavoriteDomainToEntityMapper() }
    single { DetailDtoToDomainMapper() }
    single { MovieGenreDtoToDomainMapper() }
    single { MoviesResponseDomainToUi() }
    single { MoviesResponseDtoToDomainMapper() }
    single { MovieResultsDtoToDomainMapper() }
    single { MovieResultsDomainToUIMapper(get()) }
    single { GenreDtoDomainToUIMapper() }
    single { GenreResultsDomainToUIMapper() }
    single { MovieGenreDomainToUIMapper() }
    single { DetailDomainToUIMapper() }
    single { MovieResultsDomainToDtoMapper() }
    single { MovieResultsUIToDomainMapper() }
}