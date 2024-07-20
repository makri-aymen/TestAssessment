package com.example.yassirmovieapp.core.config.routing

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.yassirmovieapp.presentation.discoverMovies.DiscoverMoviesScreen
import com.example.yassirmovieapp.presentation.discoverMovies.viewmodel.DiscoverMoviesViewModel
import com.example.yassirmovieapp.presentation.discoverMovies.viewmodel.MovieDetailsEvent
import com.example.yassirmovieapp.presentation.movieDetails.MovieDetailsScreen
import com.example.yassirmovieapp.presentation.movieDetails.viewmodel.MovieDetailsViewModel

@Composable
fun NavigationGraph(
    startDestination: String = Route.DiscoverMovies.route
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
            composable(
                route = "${Route.MovieDetails.route}/{movieId}",
                arguments = listOf(
                    navArgument(name = "movieId"){
                        type = NavType.IntType
                    }
                )
            ){ backStackEntry ->
                val viewModel: MovieDetailsViewModel = hiltViewModel()
                val movieId : Int = backStackEntry.arguments!!.getInt("movieId")
                LaunchedEffect(Unit) {
                    viewModel.onEvent(MovieDetailsEvent.InitializeData(movieId))
                }
                MovieDetailsScreen(
                    viewModel = viewModel,
                    movieId = movieId
                )
            }

            composable(route = Route.DiscoverMovies.route){
                val viewModel: DiscoverMoviesViewModel = hiltViewModel()
                val movies = viewModel.movies.collectAsLazyPagingItems()
                DiscoverMoviesScreen(
                    movies = movies,
                    navigate = {
                        navController.navigate("${Route.MovieDetails.route}/${it}")
                    }
                )
            }
    }
}