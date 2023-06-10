package com.example.marsneocasestudy.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.marsneocasestudy.views.adapters.CurrencyAdapter
import com.example.marsneocasestudy.R
import com.example.marsneocasestudy.SharedViewModel
import com.example.marsneocasestudy.Utils.CurrencyType
import com.example.marsneocasestudy.api.CurrencyData
import com.example.marsneocasestudy.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private val adapter = CurrencyAdapter(){
        selectCurrency(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        initView()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        sharedViewModel.currentPage.value = R.id.home

        sharedViewModel.name.observe(viewLifecycleOwner){
            binding.welcomeText.text = getString(R.string.welcome_with_name, it)
        }

        sharedViewModel.walletId.observe(viewLifecycleOwner){
            binding.walletText.text = getString(R.string.wallet_id, it)
        }

        sharedViewModel.currentBalance.observe(viewLifecycleOwner){
            binding.balanceText.text = getString(R.string.balance_text, sharedViewModel.currencyType.value, it)
        }

        sharedViewModel.currencyType.observe(viewLifecycleOwner){
            binding.balanceText.text = getString(R.string.balance_text, it, sharedViewModel.currentBalance.value)
        }

        sharedViewModel.currencies.observe(viewLifecycleOwner){
            adapter.setItems(it)
        }
    }

    private fun initView() {
        binding.addMoneyButton.setOnClickListener {
            sharedViewModel.addMoney()
        }

        binding.historyButton.setOnClickListener {
            sharedViewModel.currentPage.value = 1
        }

        binding.recyclerView.adapter = adapter
    }

    private fun selectCurrency(it: CurrencyData) {
        BottomDialog.showDialog(
            it,
            dismissed = {},
            onBuyClicked = {
                amount, currencyData ->
                run {
                    sharedViewModel.makeTransaction(amount, currencyData)
                }
            },
            parentFragmentManager
        )
    }


}