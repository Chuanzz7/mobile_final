package com.example.mobile_final.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mobile_final.R
import com.example.mobile_final.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)


        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController

        binding.bottomNavigation.setupWithNavController(navController)
        setContentView(binding.root)
    }
}