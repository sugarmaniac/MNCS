package com.example.marsneocasestudy.api

import com.example.marsneocasestudy.Utils.CurrencyType

data class Data(
    val CNY: Double,
    val EUR: Double,
    val GBP: Double,
    val RUB: Double,
    val USD: Double
)

data class CurrencyData(
    val currencyType: CurrencyType,
    val value : Double
)

fun dataToCurrencyData(data : Data) : MutableList<CurrencyData> {
    return mutableListOf(
        CurrencyData(CurrencyType.CNY, 1/data.CNY),
        CurrencyData(CurrencyType.EUR, 1/data.EUR),
        CurrencyData(CurrencyType.GBP, 1/data.GBP),
        CurrencyData(CurrencyType.RUB, 1/data.RUB),
        CurrencyData(CurrencyType.USD, 1/data.USD),
    )

}