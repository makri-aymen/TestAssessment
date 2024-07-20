package com.example.yassirmovieapp.data.mappers

import com.example.yassirmovieapp.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class DiscoverMoviesMapper(
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
    val page: Int
)