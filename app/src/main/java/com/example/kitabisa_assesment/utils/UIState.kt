package com.example.kitabisa_assesment.utils

sealed class UIState<out T> {
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val message: String, val throwable: Throwable? = null) : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
}