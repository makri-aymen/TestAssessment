package com.example.yassirmovieapp.domain.repository

import androidx.paging.PagingData
import com.example.yassirmovieapp.core.utils.DataState
import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getDiscoverMovies(): Flow<PagingData<Movie>>
    suspend fun getMovieDetail(movieId: Int): DataState<MovieDetail>
}