package com.example.marsneocasestudy.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//class Api {
//
//    private val BASE_DEVICE_URL =
//
//    private val api = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .baseUrl("https://api.freecurrencyapi.com/v1/")
//        .build()
//        .create(DataApi::class.java)
//
//
//}



interface DataApi {

    @GET("latest?apikey=FhwsKLmBxFmR4jWLo3p5rhQkQpM3LY3brIaJrtek&currencies=EUR%2CUSD%2CRUB%2CCNY%2CGBP&base_currency=TRY")
    suspend fun getData() : Response<CurrencyResponse>
}