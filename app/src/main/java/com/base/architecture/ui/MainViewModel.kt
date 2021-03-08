package com.base.architecture.ui

import com.mindorks.bootcamp.instagram.ui.base.BaseViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    networkHelper: NetworkHelper
) :
    BaseViewModel(networkHelper) {
    override fun onCreate() {

    }
}