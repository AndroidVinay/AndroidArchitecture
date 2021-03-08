package com.base.architecture.util.network

import android.content.Context
import android.widget.Toast
import com.base.architecture.repository.Result
import com.mindorks.bootcamp.instagram.ui.base.BaseViewModel


abstract class ResponseHandler<T> constructor(
    val result: Result<T?>,
    val mContext: Context,
    val viewModel: BaseViewModel
) {


    init {
        when (result) {
            is Result.success -> {
                result.data?.let {
                    onSuccess(it)
                }
            }
            is Result.failure -> {
                onError(result.exception)
            }

            is Result.Loading -> {
                onLoading()
            }

            else -> {

            }
        }
    }

    abstract fun onSuccess(data: T)

    open fun onError(throwable: Throwable) {
        viewModel.handleNetworkError(throwable)
    }

    open fun onLoading() {
        Toast.makeText(mContext, "loading", Toast.LENGTH_LONG).show()
    }

}