package com.example.marsneocasestudy.database

import androidx.lifecycle.LiveData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionsDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransaction(transactionEntity: TransactionEntity)

    @Query("Select * from transactions_table")
    fun readAll(): LiveData<List<TransactionEntity>>

}
