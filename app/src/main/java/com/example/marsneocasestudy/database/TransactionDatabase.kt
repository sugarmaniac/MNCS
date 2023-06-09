package com.example.marsneocasestudy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionsDao

    companion object{
        @Volatile
        private var Instance: TransactionDatabase? = null

        fun getDataBase(context: Context): TransactionDatabase {
            val tempInstance = Instance
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionDatabase::class.java,
                    "transaction_database"
                ).build()
                Instance = instance
                return instance
            }
        }
    }
}