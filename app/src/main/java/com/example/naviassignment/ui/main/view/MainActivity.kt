package com.example.naviassignment.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naviassignment.R
import com.example.naviassignment.data.api.RetrofitService
import com.example.naviassignment.data.repository.MainRepository
import com.example.naviassignment.databinding.ActivityMainBinding
import com.example.naviassignment.ui.main.adapter.PullRequestsAdapter
import com.example.naviassignment.ui.main.viewmodel.PullRequestVMFactory
import com.example.naviassignment.ui.main.viewmodel.PullRequestsVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PullRequestsVM
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = PullRequestsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        this.title = getString(R.string.title);

        viewModel = ViewModelProvider(this, PullRequestVMFactory(MainRepository(retrofitService))).get(PullRequestsVM::class.java)
        binding.rvClosedPr.layoutManager = LinearLayoutManager(this)
        binding.rvClosedPr.adapter = adapter
        viewModel.pullRequestList.observe(this, Observer {
//            Log.e("sanath", "onCreate: $it")
            if(it!=null){
                adapter.setList(it)
            }
        })
        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getClosedPullRequests()
    }
}