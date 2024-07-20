package com.example.yassirmovieapp.core.config.routing

sealed class Route(
    val route: String
) {
    data object DiscoverMovies : Route(route = "DiscoverMovies")
    data object MovieDetails : Route(route = "MovieDetails")
}