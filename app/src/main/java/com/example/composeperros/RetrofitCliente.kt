package com.example.composeperros

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCliente{
    var retrofitBase = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var retrofitService  = retrofitBase.create(DogApiService::class.java)
}