package com.nyt.nytimes.rest

import com.nyt.nytimes.data.model.NewsResponse
import com.nyt.nytimes.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIInterface {
    @GET(Constants.API_ENDPOINT)
    suspend fun getNews(@Path("period") period: Int,
                            @Query("api-key") apikey: String): NewsResponse
}