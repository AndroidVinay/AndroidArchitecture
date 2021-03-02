package com.base.architecture.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

private val TAG: String = "AppDebug"

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher,apiCall: suspend ()->T?):Result<T?>{

    return withContext(dispatcher){
        try {
            withTimeout(6000L){
                Result.success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Result.failure(throwable)
        }
    }
}

suspend fun <T> safeDBCall(dispatcher: CoroutineDispatcher,apiCall: suspend ()->T?):Result<T?>{

    return withContext(dispatcher){
        try {
            withTimeout(2000L){
                Result.success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Result.failure(throwable)
        }
    }
}