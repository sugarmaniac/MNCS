package com.example.marsneocasestudy.di

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.MyApplication
import com.example.marsneocasestudy.api.DataApi
import com.example.marsneocasestudy.database.Repo
import com.example.marsneocasestudy.database.TransactionDatabase
import com.example.marsneocasestudy.database.TransactionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication{
        return app as MyApplication
    }

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext app:Context) = Room.databaseBuilder(
        app,
        TransactionDatabase::class.java,
        "transaction_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: TransactionDatabase) = db.transactionDao()

    @Singleton
    @Provides
    fun provideRepo(dao: TransactionsDao): Repo {
        return Repo(dao)
    }

    @Singleton
    @Provides
    fun provideApi():DataApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://api.freecurrencyapi.com/v1/")
        .build()
        .create(DataApi::class.java)

}