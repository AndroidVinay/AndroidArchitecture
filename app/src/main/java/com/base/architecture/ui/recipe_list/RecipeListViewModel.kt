package com.base.architecture.ui.recipe_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.base.architecture.model.Recipe
import com.base.architecture.repository.RecipeRepository
import com.base.architecture.repository.Result
import com.mindorks.bootcamp.instagram.ui.base.BaseViewModel
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    val repository: RecipeRepository,
    networkHelper: NetworkHelper
) :
    BaseViewModel(networkHelper) {

    fun getRecipeList(): LiveData<Result<List<Recipe>?>> {
        return repository.getRecipes().asLiveData()
    }

    override fun onCreate() {
    }


}


