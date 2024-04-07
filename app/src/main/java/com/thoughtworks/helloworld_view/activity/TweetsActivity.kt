package com.thoughtworks.helloworld_view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.adapters.TweetAdapter
import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.room.dataSource.TweetDataSource
import java.io.IOException


class TweetsActivity : AppCompatActivity(R.layout.tweets_layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val tweetAdapter = TweetAdapter()

        val dataSource = TweetDataSource(applicationContext as MyApplication)
        dataSource.insertDataFromJsonFile()

        val tweetDataListFlow = dataSource.fetchTweets()
        tweetDataListFlow.onEach {
            tweetAdapter.setTweetDataList(it)
        }.launchIn(lifecycleScope)

        recyclerView.adapter = tweetAdapter
    }
}