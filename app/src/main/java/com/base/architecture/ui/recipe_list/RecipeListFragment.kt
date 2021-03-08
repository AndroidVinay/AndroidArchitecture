package com.base.architecture.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import com.base.architecture.R
import com.base.architecture.databinding.RecipeListFragmentBinding
import com.base.architecture.model.Recipe
import com.base.architecture.ui.recipe_cart.RecipeCartFragment
import com.base.architecture.util.network.ResponseHandler
import com.mindorks.bootcamp.instagram.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : BaseFragment<RecipeListFragmentBinding, RecipeListViewModel>(),
    OnRowClick {

    private lateinit var adapter: RecipeListAdapter

    private var cartList = mutableListOf<Recipe>()



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = RecipeListAdapter(arrayListOf(), this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.recyclerView.context, OrientationHelper.VERTICAL)
        )
        binding.recyclerView.adapter = adapter
        viewModel.getRecipeList();

        binding.floatingActionButton.setOnClickListener {

            val bundle = Bundle()
            val tempArray = arrayListOf<Recipe>()
            tempArray.addAll(cartList)
            bundle.putParcelableArrayList("cartList", tempArray)
            val recipeCartFragment = RecipeCartFragment()
            recipeCartFragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.navMainContainer, RecipeCartFragment(), "")
                ?.addToBackStack(null)
                ?.commit()

        }

        viewModel.getRecipeList().observe(viewLifecycleOwner, Observer {
            it?.let { result ->
//                when (result) {
//                    is Result.success -> {
//                        result.data?.let {
//                            showRecipes(it)
//                        }
//                    }
//                    is Result.failure -> {
//                       Toast.makeText(activity, result.exception.message , Toast.LENGTH_LONG).show()
//                    }
//
//                    is Result.Loading -> {
//                        Toast.makeText(activity, "loading", Toast.LENGTH_LONG).show()
//                    }
//
//                    else -> {
//
//                    }
//                }
//            ResponseHandler<List<Recipe>>(result = result,null,null)

                object : ResponseHandler<List<Recipe>>(
                    result = result,
                    mContext,
                    viewModel = viewModel
                ) {
                    override fun onSuccess(data: List<Recipe>) {
                        showRecipes(data)
                    }

                }

            }
        })
    }

    override fun onResume() {
        super.onResume()
        Log.d("wait", "" + viewModel.repository)
    }

    fun showRecipes(recipes: List<Recipe>) {
        adapter.addData(recipes)
        adapter.notifyDataSetChanged();

    }

    override fun onItemSelected(position: Int, item: Recipe) {
        Toast.makeText(activity, "" + item.name, Toast.LENGTH_SHORT).show()
        cartList.add(item)
        if (cartList.size > 0) {
            binding.tvCount.visibility = View.VISIBLE
            binding.tvCount.setText(cartList.size.toString())
        }
    }


    override fun setupView(view: View) {

    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> RecipeListFragmentBinding
        get() = RecipeListFragmentBinding::inflate

}