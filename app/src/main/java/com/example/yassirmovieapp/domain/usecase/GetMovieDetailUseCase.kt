package com.example.yassirmovieapp.domain.usecase

import com.example.yassirmovieapp.core.utils.DataState
import com.example.yassirmovieapp.domain.model.MovieDetail
import com.example.yassirmovieapp.domain.repository.MoviesRepository

class GetMovieDetailUseCase(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int): DataState<MovieDetail> {
        return moviesRepository.getMovieDetail(movieId)
    }
}