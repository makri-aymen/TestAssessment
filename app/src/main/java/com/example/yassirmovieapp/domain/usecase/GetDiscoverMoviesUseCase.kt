package com.example.yassirmovieapp.domain.usecase

import androidx.paging.PagingData
import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetDiscoverMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return moviesRepository.getDiscoverMovies()
    }
}