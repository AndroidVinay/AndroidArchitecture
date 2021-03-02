package com.base.architecture.ui.recipe_list

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.architecture.R
import com.base.architecture.databinding.RecipeListItemBinding
import com.base.architecture.model.Recipe
import com.bumptech.glide.Glide

class RecipeListAdapter(private val recipes: MutableList<Recipe>, private val onRowClick: OnRowClick) :
    RecyclerView.Adapter<RecipeListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder =
        RecipeListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recipe_list_item, parent, false
            ), onRowClick
        )

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun addData(recipes: List<Recipe>){
        this.recipes.apply {
            clear()
            addAll(recipes)
        }
    }
}



class RecipeListViewHolder(private val itemView: View, private val onRowClick: OnRowClick) :
    RecyclerView.ViewHolder(itemView) {
    var binding: RecipeListItemBinding
    init {
        binding = RecipeListItemBinding.bind(itemView)
    }
    fun bind(item: Recipe) = with(itemView) {
        binding.btnAddToCart.setOnClickListener {
            onRowClick.onItemSelected(adapterPosition, item)
        }

        Glide.with(this).load(item.image).into(binding.imageViewAvatar);
        binding.tvName.setText(item.name)
        binding.tvPrice.setText(item.price)
    }


}

interface OnRowClick {
    fun onItemSelected(position: Int, item: Recipe)
}
