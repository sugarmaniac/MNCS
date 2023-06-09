package com.example.marsneocasestudy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_table")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "date") val value: Int,
    @ColumnInfo(name = "currency_name") val name: String = "",
    @ColumnInfo(name = "cost") val cost: String = "",
    @ColumnInfo(name = "buy_price") val buyPrice: String = "",
)

