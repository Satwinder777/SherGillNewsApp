package com.shergill.sgpgym.retrofit.interfaces

import com.shergill.sgpgym.model.EveryThingResponce
import com.shergill.sgpgym.model.TopHeadlinesResponce
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApiInterface {


    @GET("everything?q=everything&apiKey=5dcc2702572d48eeac68a6c0bc9e07ab")
    fun getAllNews(): Call<EveryThingResponce>


//    ?country=us&apiKey=5dcc2702572d48eeac68a6c0bc9e07ab

    @GET("top-headlines")
     suspend fun topHeadlines( @Query("country") country: String,@Query("apiKey") apiKey: String):Response<TopHeadlinesResponce>
}