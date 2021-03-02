package com.base.architecture.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.base.architecture.model.Recipe

@Database(entities = [Recipe::class], version = 1,exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        val DATABASE_NAME: String = "recipe_db"
    }
}