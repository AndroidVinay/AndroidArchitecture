package com.base.architecture.di

import com.base.architecture.api.ApiService
import com.base.architecture.local.RecipeDao
import com.base.architecture.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object RepositoryModule {


    @FragmentScoped
    @Provides
    fun getRecipeRepository(apiService: ApiService,recipeDao: RecipeDao): RecipeRepository {
        return RecipeRepository(apiService,recipeDao)
    }
}