package com.example.naviassignment.data.api

import com.example.naviassignment.Constants
import com.example.naviassignment.data.model.PullRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    companion object {
        private var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

    @GET("/repos/{owner}/{repo}/pulls")
    fun getPullRequests(@Path("owner") user: String,
                  @Path("repo") repo: String,
                  @Query("state") sort: String?
    ): Call<List<PullRequest>>
}














