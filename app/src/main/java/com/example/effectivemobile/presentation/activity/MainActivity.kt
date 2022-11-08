package com.example.effectivemobile.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.host_global) as NavHostFragment
    }
    private val navController: NavController by lazy { navHostFragment.navController }
    private val graphInflater: NavInflater by lazy { navController.navInflater }

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)
            defineStartDestination()
        }
    }

    private fun defineStartDestination() {
        val navGraph = graphInflater.inflate(R.navigation.navigation_global)
        val startDestination = R.id.mainFragment

        navGraph.setStartDestination(startDestination)

        navController.graph = navGraph
    }
}