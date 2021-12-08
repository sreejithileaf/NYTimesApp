package com.nyt.nytimes.rest

import com.nyt.nytimes.data.model.NewsResponse
import com.nyt.nytimes.utils.Constants

class NYTimesRepository {

    suspend fun getNews(apiInterface: APIInterface, period: Int): NewsResponse {
        return apiInterface.getNews(period = period, apikey = Constants.API_KEY)
    }
}