package com.base.architecture.di

import android.content.Context
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HelperModule {

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext appContext: Context): NetworkHelper =
        NetworkHelper(appContext)

}