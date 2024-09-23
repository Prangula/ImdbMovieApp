package com.imdbmovieapp.di

import com.imdbmovieapp.data.local.mapper.MovieDomainToMovieEntityMapper
import com.imdbmovieapp.data.local.mapper.MovieEntityToMovieDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.DetailDtoToDetailDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.GenreDtoToGenreDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.PopularDtoToPopularDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.SearchDtoToSearchDomainMapper
import com.imdbmovieapp.data.remote.mapper.dtoToDomain.TopRatedDtoToTopRatedDomainMapper
import org.koin.dsl.module

val mapperModule = module {
    single { MovieEntityToMovieDomainMapper() }
    single { MovieDomainToMovieEntityMapper() }
    single { PopularDtoToPopularDomainMapper() }
    single { TopRatedDtoToTopRatedDomainMapper() }
    single { DetailDtoToDetailDomainMapper() }
    single { SearchDtoToSearchDomainMapper() }
    single { GenreDtoToGenreDomainMapper() }
}