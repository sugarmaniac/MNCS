package com.example.marsneocasestudy.views

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.marsneocasestudy.R
import com.example.marsneocasestudy.Utils.CurrencyType
import com.example.marsneocasestudy.Utils.getCurrencyIcon
import com.example.marsneocasestudy.Utils.getCurrencyName
import com.example.marsneocasestudy.api.CurrencyData
import com.example.marsneocasestudy.databinding.BottomDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialog(
    val currency: CurrencyData,
    val dismissed : () -> Unit,
    val onBuyClicked : (amount: Float, currencyData : CurrencyData) -> Unit,
) : BottomSheetDialogFragment() {

    private lateinit var binding : BottomDialogLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomDialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val behaviour = BottomSheetBehavior.from(view.parent as View)
        behaviour.state = BottomSheetBehavior.STATE_EXPANDED

        initView()
    }

    private fun initView() {
        binding.topText.text = getString(R.string._1s_you_want_to_buy, "/TL" + getCurrencyName(requireContext(), currency.currencyType))
        Glide.with(requireContext()).load(getCurrencyIcon(requireContext(), currency.currencyType)).into(binding.icon)
        binding.price.text = getString(R.string.price_tl_start, currency.value)

        binding.firstInput.doOnTextChanged { text, _, _, _ ->
            if (text != ""){
                binding.secondInput.setText((text.toString().toFloat() * currency.value).toString())
            } else {
                binding.secondInput.setText("0")
            }
        }

        binding.desc1.text = getString(R.string._1s_you_want_to_buy, getCurrencyName(requireContext(), currency.currencyType))

        binding.button.setOnClickListener {
            onBuyClicked.invoke(binding.firstInput.text.toString().toFloat(), currency)
            dismiss()
        }

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    companion object{
        fun showDialog(
            currency: CurrencyData,
            dismissed: () -> Unit,
            onBuyClicked: (amount : Float, currencyData : CurrencyData) -> Unit,
            fragmentManager: FragmentManager
        ) = BottomDialog(currency, dismissed, onBuyClicked).show(fragmentManager, "").apply {

        }
    }
}