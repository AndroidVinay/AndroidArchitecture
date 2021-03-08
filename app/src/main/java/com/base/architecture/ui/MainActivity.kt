package com.base.architecture.ui

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.base.architecture.R
import com.base.architecture.databinding.ActivityMainBinding
import com.base.architecture.extensions.setupWithNavController
import com.base.architecture.ui.base.BaseActivity
import com.base.architecture.ui.recipe_list.RecipeListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mindorks.bootcamp.instagram.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity() : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(R.id.navMainContainer, RecipeListFragment(), "")
            .commit()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {

        val navGraphIds =
            listOf(R.navigation.nav_home, R.navigation.nav_explore, R.navigation.nav_more)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = binding.bnvMain.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navMainContainer,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }


    override fun setupView(savedInstanceState: Bundle?) {

    }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate


}