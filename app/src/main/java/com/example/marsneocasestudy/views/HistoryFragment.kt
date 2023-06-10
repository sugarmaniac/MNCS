package com.example.marsneocasestudy.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.marsneocasestudy.R
import com.example.marsneocasestudy.SharedViewModel
import com.example.marsneocasestudy.databinding.FragmentHistoryBinding
import com.example.marsneocasestudy.databinding.FragmentHomeBinding
import com.example.marsneocasestudy.views.adapters.TransactionAdapter
import dagger.hilt.android.AndroidEntryPoint

class HistoryFragment : Fragment() {

    private lateinit var binding : FragmentHistoryBinding
    private val sharedViewModel : SharedViewModel by activityViewModels()

    private val adapter = TransactionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater)
        initView()
        initObserver()
        return binding.root
    }

    private fun initView() {
        sharedViewModel.currentPage.value = R.id.history

        binding.rv.adapter = adapter
    }

    private fun initObserver(){
        sharedViewModel.repo.readAllData.observe(viewLifecycleOwner){
            adapter.setItems(it)
        }
    }



}