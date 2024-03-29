package com.thoughtworks.helloworld_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.helloworld_view.adapters.TweetAdapter
import com.thoughtworks.helloworld_view.model.Sender
import com.thoughtworks.helloworld_view.model.Tweet

class TweetsActivity : AppCompatActivity() {

    private val is_use_json_file_feature_toggle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
        initUI()
    }

    private fun initUI() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        getTweetListData()
        val tweetAdapter = TweetAdapter(getTweetListData())
        recyclerView.adapter = tweetAdapter
    }

    private fun getTweetListData(): ArrayList<Tweet> {
        if (is_use_json_file_feature_toggle) {
            return arrayListOf(
                Tweet("content1", emptyList(), Sender("name1", "nick1", "avatar1"), emptyList()),
                Tweet("content2", emptyList(), Sender("name2", "nick1", "avatar1"), emptyList()),
                Tweet("content3", emptyList(), Sender("name3", "nick1", "avatar1"), emptyList()),
                Tweet("content4", emptyList(), Sender("name4", "nick1", "avatar1"), emptyList()),
                Tweet("content5", emptyList(), Sender("name5", "nick1", "avatar1"), emptyList()),
                Tweet("content6", emptyList(), Sender("name6", "nick1", "avatar1"), emptyList()),
            )
        }
        return arrayListOf(
            Tweet("content1", emptyList(), Sender("name1", "nick1", "avatar1"), emptyList()),
            Tweet("content2", emptyList(), Sender("name2", "nick1", "avatar1"), emptyList()),
            Tweet("content3", emptyList(), Sender("name3", "nick1", "avatar1"), emptyList()),
            Tweet("content4", emptyList(), Sender("name4", "nick1", "avatar1"), emptyList()),
            Tweet("content5", emptyList(), Sender("name5", "nick1", "avatar1"), emptyList()),
            Tweet("content6", emptyList(), Sender("name6", "nick1", "avatar1"), emptyList()),
        )
    }
}