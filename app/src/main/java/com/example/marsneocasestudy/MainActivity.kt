package com.example.marsneocasestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.marsneocasestudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.navBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    findNavController(binding.navHostFragment.id).navigate(R.id.action_historyFragment_to_homeFragment)
                }
                R.id.history -> {
                    findNavController(binding.navHostFragment.id).navigate(R.id.action_homeFragment_to_historyFragment)
                }
            }
            true
        }

        binding.navBar.setOnItemReselectedListener {

        }
    }
}