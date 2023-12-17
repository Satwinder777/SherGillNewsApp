package com.shergill.sgpgym.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shergill.sgpgym.model.EveryThingResponce
import com.shergill.sgpgym.model.TopHeadlinesResponce
import com.shergill.sgpgym.retrofit.RetrofitHelper
import com.shergill.sgpgym.retrofit.interfaces.MyApiInterface
import com.shergill.sgpgym.view.activity.splace.Sealedclss.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewsRepository(var service :MyApiInterface) {
    private var repo_news  = MutableLiveData<EveryThingResponce?>()
    var newsrepo : LiveData<EveryThingResponce?> = repo_news

    private var headline  = MutableLiveData<TopHeadlinesResponce?>()
    var topHeadlines : LiveData<TopHeadlinesResponce?> = headline

    suspend fun getAllNew() {
//        var etResponce:EveryThingResponce?=null
        service.getAllNews().enqueue(object:Callback<EveryThingResponce>{
            override fun onResponse(
                call: Call<EveryThingResponce>,
                response: Response<EveryThingResponce>
            ) {
                if (response.code()==200){

                    repo_news.postValue(response.body())
                    Log.e("test123", "onsuccess: ${response.body()}", )
                }else{
                    Log.e("test123", "onFailure: ${response.code()}", )
                }
            }

            override fun onFailure(call: Call<EveryThingResponce>, t: Throwable) {
                Log.e("test123", "onFailure: ${t.message}", )
            }

        })

    }

    suspend fun topHeadline():ApiResponse<TopHeadlinesResponce>{

        return try {
            ApiResponse.Loading<Boolean>()
            var response = service.topHeadlines("in",RetrofitHelper.apiKey)
            if (response.isSuccessful){
                ApiResponse.Success<TopHeadlinesResponce>(response.body() as TopHeadlinesResponce)
            }else{
                ApiResponse.Error<TopHeadlinesResponce>(response.message())
            }

        }
        catch (e:Exception){
            ApiResponse.Error<TopHeadlinesResponce>(e.message!!)
        }


//        service.topHeadlines("in",RetrofitHelper.apiKey).enqueue(object:Callback<TopHeadlinesResponce>{
//            override fun onResponse(
//                call: Call<TopHeadlinesResponce>,
//                response: Response<TopHeadlinesResponce>
//            ) {
//                if (response.code()==200){
//                    headline.postValue(response.body())
//                    Log.e("onSuccess123", "onSuccess: ${response.body()?.articles}", )
//                }else{
//                    Log.e("error123", "onResponse: ${response.message()}", )
//                }
//            }
//
//            override fun onFailure(call: Call<TopHeadlinesResponce>, t: Throwable) {
//                Log.e("error123", "onResponse: ${t.message}", )
//            }
//
//        } )
    }
}