package com.nyt.nytimes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyt.nytimes.data.model.ResultsItem
import com.nyt.nytimes.rest.APIInterface
import com.nyt.nytimes.rest.NYTimesRepository
import com.nyt.nytimes.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val appsRepository: NYTimesRepository,
    private val apiInterface: APIInterface
) : ViewModel() {

    val newsList = SingleLiveEvent<List<ResultsItem?>?>()

    val progress = SingleLiveEvent<Boolean>()
    val errorMessage = SingleLiveEvent<String?>()


    fun fetchArticles() {
        if (newsList.value != null) {
            newsList.postValue(newsList.value)
            return
        }
        progress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appsRepository.getNews(apiInterface, period = 7)
                when (response.status) {
                    "OK" -> {
                        val remoteData = response.results
                        newsList.postValue(remoteData)
                    }
                    else -> {
                        errorMessage.postValue(null)
                    }
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
                e.printStackTrace()
            } finally {
                progress.postValue(false)
            }
        }
    }
}