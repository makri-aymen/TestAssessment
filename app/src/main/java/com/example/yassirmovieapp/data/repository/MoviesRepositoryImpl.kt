package com.example.yassirmovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.yassirmovieapp.core.Constants.API_KEY
import com.example.yassirmovieapp.core.utils.DataState
import com.example.yassirmovieapp.data.datasource.MoviesPagingSource
import com.example.yassirmovieapp.data.datasource.MoviesRepositoryDatasource
import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.domain.model.MovieDetail
import com.example.yassirmovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val moviesRepositoryDatasource: MoviesRepositoryDatasource
) : MoviesRepository {

    override fun getDiscoverMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviesPagingSource(
                    moviesRepositoryDatasource = moviesRepositoryDatasource,
                )
            }
        ).flow
    }

    override suspend fun getMovieDetail(movieId: Int): DataState<MovieDetail> {
        return try {
            val movieDetail = moviesRepositoryDatasource.getMovieDetail(movieId, API_KEY)
            DataState.Success(data = movieDetail)
        }catch(e : Exception){
            DataState.Exception(exception = e)
        }
    }
}