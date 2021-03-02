package com.base.architecture.repository

sealed class Result<out T> {

    data class Loading<T>(val isLoading:Boolean = true):Result<T>()

    data class success<T>(val data:T):Result<T>()

    data class failure<T>(val exception: Throwable) :Result<T>()

}