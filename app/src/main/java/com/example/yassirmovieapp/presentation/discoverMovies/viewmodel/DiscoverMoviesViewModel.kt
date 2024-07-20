package com.example.yassirmovieapp.presentation.discoverMovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.yassirmovieapp.domain.usecase.GetDiscoverMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase
) : ViewModel() {

    val movies = getDiscoverMoviesUseCase().cachedIn(viewModelScope)

}