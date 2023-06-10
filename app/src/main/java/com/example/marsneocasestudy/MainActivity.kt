package com.example.marsneocasestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.example.marsneocasestudy.api.DataApi
import com.example.marsneocasestudy.database.Repo
import com.example.marsneocasestudy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: Repo

    @Inject
    lateinit var api : DataApi

    private lateinit var binding : ActivityMainBinding
    val viewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.repo = repo
        viewModel.api = api

        initView()
    }

    private fun initView() {
        binding.navBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    if (viewModel.currentPage.value !=0)
                    viewModel.currentPage.value = 0
                }
                R.id.history -> {
                    if (viewModel.currentPage.value !=1)
                    viewModel.currentPage.value = 1
                }
            }
            true
        }

        binding.navBar.setOnItemReselectedListener {

        }

        viewModel.currentPage.observe(this){

            if (it == 0 ){
                binding.navBar.selectedItemId = it
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_historyFragment_to_homeFragment)
            } else if (it == 1) {
                binding.navBar.selectedItemId = it
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_historyFragment)
            }
        }

        viewModel.fetchData()
    }
}