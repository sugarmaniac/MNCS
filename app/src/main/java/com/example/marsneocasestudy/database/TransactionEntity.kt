package com.example.marsneocasestudy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marsneocasestudy.Utils.CurrencyType

@Entity(tableName = "transactions_table")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "currency_type") val type: CurrencyType = CurrencyType.USD,
    @ColumnInfo(name = "cost") val cost: String = "",
    @ColumnInfo(name = "buy_price") val buyPrice: String = "",
    @ColumnInfo(name = "amount") val amount: Float = 0f
)

