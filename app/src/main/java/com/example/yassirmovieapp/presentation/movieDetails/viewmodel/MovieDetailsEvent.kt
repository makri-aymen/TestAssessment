package com.example.yassirmovieapp.presentation.discoverMovies.viewmodel

sealed class MovieDetailsEvent {
    data class InitializeData(var movieId: Int): MovieDetailsEvent()
}