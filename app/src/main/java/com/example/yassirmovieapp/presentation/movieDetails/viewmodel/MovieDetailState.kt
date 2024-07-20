package com.example.yassirmovieapp.presentation.movieDetails.viewmodel

import com.example.yassirmovieapp.domain.model.MovieDetail

sealed class MovieDetailState {
    data object Loading: MovieDetailState()
    data class Success(val movieDetail: MovieDetail): MovieDetailState()
    data class Error(val error: Exception): MovieDetailState()
}
