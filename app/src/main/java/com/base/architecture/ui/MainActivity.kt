package com.base.architecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.base.architecture.R
import com.base.architecture.databinding.ActivityMainBinding
import com.base.architecture.ui.recipe_list.RecipeListFragment
import com.base.architecture.ui.recipe_list.RecipeListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportFragmentManager.beginTransaction().replace(R.id.container, RecipeListFragment(), "")
            .commit()

    }

}