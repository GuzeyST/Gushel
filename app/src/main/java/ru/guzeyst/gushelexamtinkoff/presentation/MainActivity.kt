package ru.guzeyst.gushelexamtinkoff.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import ru.guzeyst.gushelexamtinkoff.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, ru.guzeyst.gushelexamtinkoff.R.id.nav_host_fragment)
        val bottomNavigation = binding.bottomNavigationView
        bottomNavigation.setupWithNavController(navController)
    }
}