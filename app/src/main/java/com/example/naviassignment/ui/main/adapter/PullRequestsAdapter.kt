package com.example.naviassignment.ui.main.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.naviassignment.data.model.PullRequest
import com.example.naviassignment.databinding.ItemPrPreviewBinding
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class PullRequestsAdapter: RecyclerView.Adapter<MainViewHolder>() {
    private var pullRequestList = ArrayList<PullRequest>()
    fun setList(pullRequests: List<PullRequest>) {
        this.pullRequestList = pullRequests as ArrayList<PullRequest>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPrPreviewBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val pr = pullRequestList[position]
        holder.binding.tvTitle.text = pr.title
        holder.binding.tvUserName.text = "User: "+pr.user.login
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.binding.tvOpenDate.text = "Created: "+getDate(pr.createdAt)
            holder.binding.tvClosedDate.text = "Closed: "+getDate(pr.closedAt)
        }
        else{
            holder.binding.tvOpenDate.text = "Created: "+pr.createdAt
            holder.binding.tvClosedDate.text = "Closed: "+pr.closedAt
        }
        Glide.with(holder.itemView.context).load(pr.user.avatarUrl).into(holder.binding.ivUser)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDate(createdAt: String): Any? {
        var dateString = createdAt
        var odt = OffsetDateTime.parse(dateString)
        var df = DateTimeFormatter.ofPattern("MMM dd, uuuu", Locale.ENGLISH)
        var tf = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
        return df.format(odt)+" "+tf.format(odt)
    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }
}
class MainViewHolder(val binding: ItemPrPreviewBinding) : RecyclerView.ViewHolder(binding.root) {
}