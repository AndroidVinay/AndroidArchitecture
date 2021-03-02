package com.base.architecture.ui.recipe_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.base.architecture.model.Recipe
import com.base.architecture.repository.RecipeRepository
import com.base.architecture.repository.Result
import com.base.architecture.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


class RecipeListViewModel @ViewModelInject constructor(val repository: RecipeRepository) :
    ViewModel() {

   fun getRecipeList(): LiveData<Result<List<Recipe>?>> {
        return repository.getRecipes().asLiveData()
    }
}


