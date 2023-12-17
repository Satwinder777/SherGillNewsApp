package com.shergill.sgpgym.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shergill.sgpgym.R
import com.shergill.sgpgym.databinding.ActivityTopHeadlinesBinding
import com.shergill.sgpgym.model.TopHeadlinesResponce
import com.shergill.sgpgym.repository.NewsRepository
import com.shergill.sgpgym.retrofit.RetrofitHelper
import com.shergill.sgpgym.view.activity.splace.Sealedclss.ApiResponse
import com.shergill.sgpgym.view.adapter.NewRcAdapter
import com.shergill.sgpgym.view.adapter.TopHeadlineRcAdapter
import com.shergill.sgpgym.viewmodel.MyViewModelFactory
import com.shergill.sgpgym.viewmodel.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopHeadlinesActivity : AppCompatActivity(),TopHeadlineRcAdapter.OnTopHeadlineClick {
    private lateinit var binding:ActivityTopHeadlinesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTopHeadlinesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var repo = NewsRepository(RetrofitHelper.service)
        val viewModel =
            ViewModelProvider(this, MyViewModelFactory(repo)).get(NewsViewModel::class.java)
        binding.back.setOnClickListener {
            onBackPressed()
        }

//
//        GlobalScope.launch {
//            handleApiResponse(viewModel.topHeadlines())
//
//        }
        CoroutineScope(Dispatchers.IO).launch {
            var response = viewModel.topHeadlines()
            when(response){
                is ApiResponse.Error -> {
                    Log.e("qwer123", "on error: ${response.error}", )
                }
                is ApiResponse.Loading -> {
                    Log.e("qwer123", "onLoading: ${response.t}", )
                }
                is ApiResponse.Success -> {
                    Log.e("qwer123", "onSuccess: ${response.data}", )
                    withContext(Dispatchers.Main) {
                        var adapter  = TopHeadlineRcAdapter(response.data.articles,this@TopHeadlinesActivity)
                        binding.topheadRc.adapter = adapter
                    }
                    binding.progress.visibility = View.GONE
                }
            }

        }




//        viewModel.topHeadlines.observe(this@TopHeadlinesActivity, Observer {
//            if (it?.articles!=null){

//            }
//        })


    }
    fun handleApiResponse(response: ApiResponse<TopHeadlinesResponce>) {
        when(response){
            is ApiResponse.Error -> {
                Log.e("test324", "handleApiResponse: is error call>>${response.error}", )

            }
            is ApiResponse.Loading -> {
                Log.e("test324", "handleApiResponse: is loading call>>${response.t}", )
                binding.progress.visibility = View.VISIBLE
            }
            is ApiResponse.Success -> {
                binding.progress.visibility = View.INVISIBLE

                Log.e("test321", "onCreate: ${response.data.articles}")
                var adapter  = TopHeadlineRcAdapter(response.data.articles,this)
                binding.topheadRc.adapter = adapter
//                Log.e("test324", "handleApiResponse: is Success call>>${.articles}", )
            }
        }
    }

    override fun openUrl(url: String) {
        val intent = Intent(this@TopHeadlinesActivity,WebViewActivity::class.java)
        intent.putExtra("newsUrl",url)
        startActivity(intent)
    }

}