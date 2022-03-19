package com.example.naviassignment.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.naviassignment.data.model.PullRequest
import com.example.naviassignment.data.repository.MainRepository
import retrofit2.Call
import retrofit2.Response

class PullRequestsVM constructor(private val repository: MainRepository)  : ViewModel() {

    val pullRequestList = MutableLiveData<List<PullRequest>>()
    val errorMessage = MutableLiveData<String>()

    fun getClosedPullRequests() {
        val response = repository.getPullRequests("lapce", "lapce", "closed")

        response.enqueue(object : retrofit2.Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                pullRequestList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}