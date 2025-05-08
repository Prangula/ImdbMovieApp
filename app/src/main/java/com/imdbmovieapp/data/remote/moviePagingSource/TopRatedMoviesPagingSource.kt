package com.imdbmovieapp.data.remote.moviePagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imdbmovieapp.data.remote.api.MoviesApi
import com.imdbmovieapp.data.remote.dto.MoviesResultDto

class TopRatedMoviesPagingSource(
    private val moviesApi: MoviesApi
) : PagingSource<Int, MoviesResultDto>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesResultDto>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResultDto> {
        val pageIndex = params.key ?: 1
        return try {
            val movies = movieResponse(page = pageIndex)
            val nextKey = if (movies!!.isEmpty()) {
                null
            } else {
                pageIndex + 1
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun movieResponse(page: Int): List<MoviesResultDto>? {
        val movies = moviesApi.getTopRatedMovies(page)
        return if (movies.isSuccessful) {
            movies.body()!!.results
        } else {
            null
        }
    }
}