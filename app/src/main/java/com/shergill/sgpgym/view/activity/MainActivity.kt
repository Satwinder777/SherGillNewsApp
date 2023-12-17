package com.shergill.sgpgym.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shergill.sgpgym.databinding.ActivityMainBinding
import com.shergill.sgpgym.repository.NewsRepository
import com.shergill.sgpgym.retrofit.RetrofitHelper
import com.shergill.sgpgym.view.adapter.NewRcAdapter
import com.shergill.sgpgym.viewmodel.MyViewModelFactory
import com.shergill.sgpgym.viewmodel.NewsViewModel


class MainActivity : AppCompatActivity(),NewRcAdapter.OnTopNewsClick {
    lateinit var binding :ActivityMainBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var rc = binding.myRecycler
        var repo = NewsRepository(RetrofitHelper.service)
        val viewModel =
            ViewModelProvider(this, MyViewModelFactory(repo)).get(NewsViewModel::class.java)


        viewModel.news()
        viewModel.news.observe(this, Observer { data ->

            if (data?.articles != null) {
                var adapter = NewRcAdapter(data.articles,this)
                rc.adapter = adapter
                binding.progress.visibility = View.GONE
            }
        })

        binding.sgill.setOnClickListener {
        val intent = Intent(this@MainActivity, TopHeadlinesActivity::class.java)
            startActivity(intent)

//            finishAffinity()
    }
    }

    override fun openUrl(url: String) {

        val intent = Intent(this@MainActivity,WebViewActivity::class.java)
        intent.putExtra("newsUrl",url)
        startActivity(intent)
    }
}