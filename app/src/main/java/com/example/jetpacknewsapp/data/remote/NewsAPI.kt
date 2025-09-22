package com.example.jetpacknewsapp.data.remote

import com.example.jetpacknewsapp.Constants.API_KEY
import com.example.jetpacknewsapp.data.remote.datatransferobjects.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("everything")
    suspend fun geteverything(
        @Query("pages") page : Int,
        @Query("sources") sources : String,
        @Query("apiKey") apikey : String = API_KEY,
    ): Response<NewsResponse>

}