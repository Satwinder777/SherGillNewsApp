package com.shergill.sgpgym.retrofit

import com.shergill.sgpgym.retrofit.interfaces.MyApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {
    var baseUrl = "https://newsapi.org/v2/"

    private var retrofit :Retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service:MyApiInterface = retrofit.create(MyApiInterface::class.java)
    var apiKey = "5dcc2702572d48eeac68a6c0bc9e07ab" //news api key

}