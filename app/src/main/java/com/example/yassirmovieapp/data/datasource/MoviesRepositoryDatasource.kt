package com.example.yassirmovieapp.data.datasource

import com.example.yassirmovieapp.data.mappers.DiscoverMoviesMapper
import com.example.yassirmovieapp.domain.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRepositoryDatasource {

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ) : DiscoverMoviesMapper

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
    ) : MovieDetail
}