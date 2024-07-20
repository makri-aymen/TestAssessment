package com.example.yassirmovieapp.core.config.dependecyInjection

import com.example.yassirmovieapp.core.Constants.BASE_URL
import com.example.yassirmovieapp.data.datasource.MoviesRepositoryDatasource
import com.example.yassirmovieapp.data.repository.MoviesRepositoryImpl
import com.example.yassirmovieapp.domain.repository.MoviesRepository
import com.example.yassirmovieapp.domain.usecase.GetDiscoverMoviesUseCase
import com.example.yassirmovieapp.domain.usecase.GetMovieDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependencyInjection {

    @Provides
    @Singleton
    fun provideApiInstance(): MoviesRepositoryDatasource {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesRepositoryDatasource::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesRepositoryDatasource: MoviesRepositoryDatasource
    ) : MoviesRepository {
        return MoviesRepositoryImpl(moviesRepositoryDatasource)
    }

    @Provides
    @Singleton
    fun provideGetDiscoverMoviesUseCases(
        moviesRepository: MoviesRepository
    ) : GetDiscoverMoviesUseCase {
        return GetDiscoverMoviesUseCase(moviesRepository)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailUseCase(
        moviesRepository: MoviesRepository
    ) : GetMovieDetailUseCase {
        return GetMovieDetailUseCase(moviesRepository)
    }
}