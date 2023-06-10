package com.example.marsneocasestudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsneocasestudy.api.CurrencyData
import com.example.marsneocasestudy.api.DataApi
import com.example.marsneocasestudy.api.dataToCurrencyData
import com.example.marsneocasestudy.database.Repo
import com.example.marsneocasestudy.database.TransactionEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt


class SharedViewModel
    @ViewModelScoped
    constructor()
    : ViewModel() {


    val name = MutableLiveData("Omer SEKER")
    val walletId = MutableLiveData("5221476")
    val currentBalance = MutableLiveData(0f)
    val currencyType = MutableLiveData("₺")
    val error = MutableLiveData<Int>(0)
    val currencies = MutableLiveData<List<CurrencyData>>()

    val currentPage = MutableLiveData(-1)

    lateinit var repo: Repo
    lateinit var api: DataApi


    fun fetchData(){
        viewModelScope.launch {
            currencies.postValue(dataToCurrencyData(api.getData().body()!!.data))
        }
    }
    fun addMoney(){
        currentBalance.value = currentBalance.value!! + Random.nextInt(1..1000)
    }

    fun checkMoney(moneySpent : Float){
        if (moneySpent > currentBalance.value!!){
            error.value = 1
        } else {
            error.value = 0
        }
    }

    fun makeTransaction(amount: Float, currencyData: CurrencyData) {
        viewModelScope.launch {
            repo.addTransaction(
                TransactionEntity(
                    cost = (amount*currencyData.value).toString(),
                    date = Calendar.getInstance().time.toString(),
                    buyPrice = currencyData.value.toString(),
                    type = currencyData.currencyType,
                    amount = amount
                )
            )
        }

        currentBalance.value = (currentBalance.value?.minus(amount*currencyData.value))?.toFloat()
    }


}