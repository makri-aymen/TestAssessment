package com.example.yassirmovieapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.yassirmovieapp.core.Constants.API_KEY
import com.example.yassirmovieapp.domain.model.Movie

class MoviesPagingSource (
    private val moviesRepositoryDatasource: MoviesRepositoryDatasource,
): PagingSource<Int, Movie>() {

    private var totalPages = 0

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = moviesRepositoryDatasource.getDiscoverMovies(page = page, apiKey = API_KEY)
            val movies = response.results.distinctBy { it.title } // remove duplicated articles
            totalPages = response.totalPages
            LoadResult.Page(
                data = movies,
                nextKey = if (totalPages == response.page) null else page + 1,
                prevKey = null,
            )
        } catch(e:Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}