package com.thoughtworks.helloworld_view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.adapters.TweetAdapter
import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.dataSource.TweetDataSource
import com.thoughtworks.helloworld_view.viewModel.TweetsViewModel
import java.io.IOException


class TweetsActivity : AppCompatActivity(R.layout.tweets_layout) {

    private val tweetsViewModel: TweetsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val tweetAdapter = TweetAdapter()
        prepareTweetData()
        tweetsViewModel.tweetsLiveData.observe(this) { tweets ->
            tweetAdapter.setTweetDataList(tweets)
        }
        recyclerView.adapter = tweetAdapter
    }

    private fun prepareTweetData() {
        val dataSource = TweetDataSource(applicationContext as MyApplication)
        try {
            dataSource.getAndInsertDataToDB()
        } catch (e: IOException) {
            Toast.makeText(applicationContext, "network request fail", Toast.LENGTH_LONG).show()
        }
    }
}