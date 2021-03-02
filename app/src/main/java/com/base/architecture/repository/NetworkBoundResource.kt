package com.base.architecture.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<NetworkRes, DBRes>
constructor(
private val dispatcher: CoroutineDispatcher,
private val apiCall:suspend () -> NetworkRes?,
private val dbCall:suspend () -> DBRes?,
) {

    private val TAG:String = "AppDebug"

    val result: Flow<Result<DBRes>> = flow {

        emit(returnDBValue(false))

        val result = safeApiCall(dispatcher){apiCall.invoke()}

        when (result){
            is Result.success ->{
                result.data?.let {
                    updateDB(it)
                }
            }
            is Result.failure ->{
                emit(buildError(result.exception))
            }
        }

        emit(returnDBValue(true))

    }

    private fun buildError(result: Throwable): Result<DBRes> {
            return  Result.failure(result)
    }

    private suspend fun returnDBValue(isJobCompleted:Boolean):Result<DBRes>{
        val dbResult = safeDBCall(dispatcher){
            dbCall.invoke()
        }
            return dbResult as Result<DBRes>
    }

    abstract suspend fun updateDB(networkObject: NetworkRes)

    abstract fun handleCacheSuccess(resultObj: DBRes) // make sure to return null for stateEvent


}