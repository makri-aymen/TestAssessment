package com.example.yassirmovieapp.presentation.discoverMovies.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.yassirmovieapp.R
import com.example.yassirmovieapp.core.Constants.BASE_IMAGE_URL
import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.presentation.designTokens.YassirMovieAppTheme

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() }.fillMaxWidth()
        ) {
        AsyncImage(
            modifier = Modifier
                .height(120.dp)
                .width(96.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(BASE_IMAGE_URL + movie.posterPath).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            modifier = Modifier.height(120.dp)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.body),
            )
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.labelSmall.copy(),
                color = colorResource(id = R.color.body)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    YassirMovieAppTheme(dynamicColor = false) {
        MovieCard(
            movie = Movie(
                adult = false,
                backdropPath = "/xg27NrXi7VXCGUr7MG75UqLl6Vg.jpg",
                genreIds = listOf(
                    16,
                    10751,
                    12,
                    35
                ),
                id = 1022789,
                originalLanguage = "en",
                originalTitle = "Inside Out 2",
                overview = "Teenager Riley's mind headquarters is undergoing a sudden demolition to make room for something entirely unexpected: new Emotions! Joy, Sadness, Anger, Fear and Disgust, who’ve long been running a successful operation by all accounts, aren’t sure how to feel when Anxiety shows up. And it looks like she’s not alone.",
                popularity = 5696.178,
                posterPath = "/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg",
                releaseDate = "2024-06-11",
                title = "Inside Out 2",
                video = false,
                voteAverage = 7.696,
                voteCount = 1853
            )
        )
    }
}