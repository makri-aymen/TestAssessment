package com.example.yassirmovieapp.core.utils

sealed class DataState<T>{
    data class Success<T>(val data: T) : DataState<T>()
    data class Exception<T>(val exception: kotlin.Exception) : DataState<T>()
}