package com.example.yassirmovieapp.presentation.discoverMovies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.presentation.Dimens.MediumPadding1
import com.example.yassirmovieapp.presentation.discoverMovies.ui.MoviesList

@Composable
fun DiscoverMoviesScreen(
    movies: LazyPagingItems<Movie>,
    navigate:(Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        MoviesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            movies = movies,
            onClick = {
                navigate(it.id)
            }
        )
    }
}