package com.imdbmovieapp.data.local.movie_paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imdbmovieapp.data.local.dao.FavoriteMovieDao
import com.imdbmovieapp.data.remote.dto.MoviesResultDto
import kotlinx.coroutines.flow.first

class FavoriteMoviesPagingSource(
    private val favoriteMovieDao: FavoriteMovieDao
) : PagingSource<Int, MoviesResultDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResultDto> {
        val page = params.key ?: 0
        return try {
            val movies = favoriteMovieDao.getAllMovies()
            val moviesList = movies.first() // or use .toList() if needed

            LoadResult.Page(
                data = moviesList,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (moviesList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesResultDto>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}