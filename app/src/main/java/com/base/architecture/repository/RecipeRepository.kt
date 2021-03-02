package com.base.architecture.repository

import com.base.architecture.api.ApiService
import com.base.architecture.local.RecipeDao
import com.base.architecture.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RecipeRepository @Inject constructor(val apiService: ApiService, val recipeDao: RecipeDao) {

//    fun getRecipes(): Flow<Result<List<Recipe>>> {
//        return object :
//            NetworkBoundResource<List<Recipe>, List<Recipe>>(dispatcher = IO, apiCall = {
//                apiService.getRecipes()
//            },
//                dbCall = {
//                    recipeDao.get()
//                }) {
//            override suspend fun updateDB(networkObject: List<Recipe>) {
//                val recipes = networkObject.toList()
//                withContext(IO) {
//                    recipes.forEach { recipe ->
//                        recipeDao.insert(recipe)
//                    }
//                }
//
//            }
//
//            override fun handleCacheSuccess(resultObj: List<Recipe>) {
////                TODO("Not yet implemented")
//            }
//
//
//        }.result
//
//    }

//    fun getRecipes(): Flow<Result<List<Recipe>?>> = flow {
//
//        emit(Result.Loading(true))
//
//        emit(safeApiCall(Dispatchers.IO, apiCall = {
//            apiService.getRecipes();
//        }))
//
//    }


    fun getRecipes(): Flow<Result<List<Recipe>?>> = flow {

        emit(safeDBCall(Dispatchers.IO, apiCall = {
            recipeDao.get()
        }))

    }
}