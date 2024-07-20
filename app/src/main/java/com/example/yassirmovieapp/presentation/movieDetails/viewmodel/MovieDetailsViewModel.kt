package com.example.yassirmovieapp.presentation.movieDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yassirmovieapp.core.utils.DataState
import com.example.yassirmovieapp.domain.model.MovieDetail
import com.example.yassirmovieapp.domain.usecase.GetMovieDetailUseCase
import com.example.yassirmovieapp.presentation.discoverMovies.viewmodel.MovieDetailsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
) : ViewModel(), ContainerHost<MovieDetailState,Any> {
    val viewModelToken :Int = Random.nextInt()

    override val container: Container<MovieDetailState, Any> = container(MovieDetailState.Loading)

    fun onEvent(event: MovieDetailsEvent){
        when(event) {
            is MovieDetailsEvent.InitializeData -> initializeData(event.movieId)
            else -> {
                throw Exception("Event not handled yet")
            }
        }
    }

    private fun initializeData(movieId: Int) {
        intent {
            reduce {
                MovieDetailState.Loading
            }
            viewModelScope.launch {
                when(val result: DataState<MovieDetail> = getMovieDetailUseCase(movieId)){
                    is DataState.Success<*> -> {
                        reduce {
                            MovieDetailState.Success(result.data as MovieDetail)
                        }
                    }
                    is DataState.Exception -> {
                        reduce {
                            MovieDetailState.Error(error = result.exception)
                        }
                    }
                }
            }
        }
    }
}