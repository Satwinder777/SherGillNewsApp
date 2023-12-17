package com.shergill.sgpgym.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shergill.sgpgym.model.EveryThingResponce
import com.shergill.sgpgym.model.TopHeadlinesResponce
import com.shergill.sgpgym.repository.NewsRepository
import com.shergill.sgpgym.view.activity.splace.Sealedclss.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private var repo : NewsRepository):ViewModel() {

    init {

    }

    var news : LiveData<EveryThingResponce?> = repo.newsrepo
    var topHeadlines : LiveData<TopHeadlinesResponce?> = repo.topHeadlines



   fun news(){
       viewModelScope.launch(Dispatchers.IO) {
           repo.getAllNew()
        }
    }
    suspend fun topHeadlines(): ApiResponse<TopHeadlinesResponce> {
      return repo.topHeadline()
    }
}