package com.imdbmovieapp.data.remote.movie_paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imdbmovieapp.data.remote.dto.MoviesResultDto

class MoviesPagingSource(
    private val results: List<MoviesResultDto>?
) : PagingSource<Int, MoviesResultDto>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesResultDto>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResultDto> {
        val page = params.key ?: 1
        return LoadResult.Page(
            data = results ?: emptyList(),
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (results.isNullOrEmpty()) null else page + 1
        )
    }
}