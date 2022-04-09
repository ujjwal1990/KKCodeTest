package com.kk.codetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.kk.codetest.R
import com.kk.codetest.ui.menu.MenuFragment
import com.kk.codetest.ui.welcome.WelcomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val defaultHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = (defaultHostFragment as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, AppBarConfiguration(navController.graph))
    }
}