package com.example.yassirmovieapp.presentation.movieDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.yassirmovieapp.R
import com.example.yassirmovieapp.core.Constants
import com.example.yassirmovieapp.presentation.Dimens
import com.example.yassirmovieapp.presentation.discoverMovies.viewmodel.MovieDetailsEvent
import com.example.yassirmovieapp.presentation.movieDetails.viewmodel.MovieDetailState
import com.example.yassirmovieapp.presentation.movieDetails.viewmodel.MovieDetailsViewModel
import com.example.yassirmovieapp.presentation.ui.ErrorStateUi
import com.example.yassirmovieapp.presentation.ui.LoadingStateUi

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MovieDetailsViewModel,
) {
    val state by viewModel.container.stateFlow.collectAsState<MovieDetailState>()
    val context = LocalContext.current
    when(state) {
        is MovieDetailState.Loading -> {
            LoadingStateUi()
        }
        is MovieDetailState.Error -> {
            ErrorStateUi(
                error = (state as MovieDetailState.Error).error,
                tryAgain = {
                    viewModel.onEvent(MovieDetailsEvent.InitializeData(movieId))
                }
            )
        }
        is MovieDetailState.Success -> {
            val movieDetail = (state as MovieDetailState.Success).movieDetail
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.MediumPadding1)
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(230.dp)
                        .width(150.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .align(Alignment.CenterHorizontally),
                    model = ImageRequest.Builder(context).data(Constants.BASE_IMAGE_URL + movieDetail.posterPath).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = movieDetail.title,
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.headlineMedium,
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = movieDetail.releaseDate,
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = movieDetail.overview,
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}