package com.base.architecture.api

import com.base.architecture.model.Recipe
import retrofit2.http.GET

interface ApiService {

    @GET("he-public-data/reciped9d7b8c.json")
    suspend fun getRecipes(): List<Recipe>
}