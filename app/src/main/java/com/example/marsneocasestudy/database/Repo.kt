package com.example.marsneocasestudy.database

import androidx.lifecycle.LiveData
import javax.inject.Inject

class Repo
    @Inject
        constructor(private val transactionsDao: TransactionsDao) {

    val readAllData: LiveData<List<TransactionEntity>> = transactionsDao.readAll()

    suspend fun addTransaction(transactionEntity: TransactionEntity){
        transactionsDao.addTransaction(transactionEntity)
    }
}