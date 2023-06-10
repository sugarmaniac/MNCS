package com.example.marsneocasestudy.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

enum class CurrencyType(val type: String){
    USD("usd"),
    EUR("eur"),
    GBP("gbp"),
    RUB("rub"),
    CNY("cny")
}

fun getCurrencyName(context: Context , currencyType: CurrencyType) : String{
    val identifier = context.resources.getIdentifier(currencyType.type, "string", context.packageName)
    return context.getString(identifier)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun getCurrencyIcon(context: Context, currencyType: CurrencyType): Int{
    return context.resources.getIdentifier(currencyType.type, "mipmap", context.packageName)
}