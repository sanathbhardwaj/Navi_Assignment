package com.example.naviassignment.data.repository

import com.example.naviassignment.data.api.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getPullRequests(owner:String, repo:String, state:String) =
        retrofitService.getPullRequests(owner, repo, state)
}