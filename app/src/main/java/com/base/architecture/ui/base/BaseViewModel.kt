package com.mindorks.bootcamp.instagram.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.base.architecture.R
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import javax.net.ssl.HttpsURLConnection


abstract class BaseViewModel(
    val networkHelper: NetworkHelper
) : ViewModel() {

    override fun onCleared() {
//        compositeDisposable.dispose()
        super.onCleared()
    }

    init {
        onCreate()
    }

    val messageStringId: MutableLiveData<Int> = MutableLiveData()

    val messageString: MutableLiveData<String> = MutableLiveData()

    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(R.string.network_connection_error)
            false
        }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    public fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status) {
                    -1 -> messageStringId.postValue(R.string.network_default_error)
                    0 -> messageStringId.postValue(R.string.server_connection_error)
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        forcedLogoutUser()
                        messageStringId.postValue(R.string.server_connection_error)
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(R.string.network_internal_error)
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(R.string.network_server_not_available)
                    else -> messageString.postValue(message)
                }
            }
        }

    protected open fun forcedLogoutUser() {
        // do something
    }

    abstract fun onCreate()
}