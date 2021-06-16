package com.reo.running.runnershigh2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.reo.running.runnershigh2.databinding.ActivityMainBinding
import com.reo.running.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_container)
        setupWithNavController(binding.bottomNavigation, navController)
    }
}