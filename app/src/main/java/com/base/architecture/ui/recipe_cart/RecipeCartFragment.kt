package com.base.architecture.ui.recipe_cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import com.base.architecture.databinding.RecipeCartFragmentBinding
import com.base.architecture.model.Recipe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeCartFragment : Fragment(), OnRowClick {

    companion object {
        fun newInstance() = RecipeCartFragment()
    }

    private var _binding: RecipeCartFragmentBinding? = null
    private val binding get() = _binding!!
    val viewModel: RecipeCartViewModel by viewModels()
    private lateinit var adapter: RecipeCartAdapter
    private var cartList = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecipeCartFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("wait", "" + viewModel.repository)
        arguments?.containsKey("cartList").let {
            if (it == true) {
                arguments?.getParcelableArrayList<Recipe>("cartList")?.let { it1 ->
                    cartList.addAll(
                        it1
                    )
                }
            }
        }

        adapter = RecipeCartAdapter(cartList, this)
        binding.recyclerViewCart.addItemDecoration(
            DividerItemDecoration(binding.recyclerViewCart.context, OrientationHelper.VERTICAL)
        )
        binding.recyclerViewCart.adapter = adapter
    }

    override fun onItemSelected(position: Int, item: Recipe) {

    }

}