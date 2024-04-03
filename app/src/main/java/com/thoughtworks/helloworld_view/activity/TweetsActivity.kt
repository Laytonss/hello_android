package com.thoughtworks.helloworld_view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.adapters.TweetAdapter
import com.thoughtworks.helloworld_view.model.Sender
import com.thoughtworks.helloworld_view.model.Tweet

const val IS_USE_JSON_FILE_FEATURE_TOGGLE = false
const val IS_USE_ASSET_FILE_FEATURE_TOGGLE = true

class TweetsActivity : AppCompatActivity(R.layout.tweets_layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val tweetAdapter = TweetAdapter(getTweetListData())
        recyclerView.adapter = tweetAdapter
    }

    private fun getTweetListData(): List<Tweet> {
        val gson = Gson()
        if (IS_USE_JSON_FILE_FEATURE_TOGGLE) {
            resources.openRawResource(R.raw.tweets).use { inputStream ->
                val json = inputStream.bufferedReader().readText()
                return gson.fromJson(json, Array<Tweet>::class.java).toList().filter { it.error == null && it.unknownError == null }
            }
        }
        if (IS_USE_ASSET_FILE_FEATURE_TOGGLE) {
            assets.open("tweets.json").use { inputStream ->
                val json = inputStream.bufferedReader().readText()
                return gson.fromJson(json, Array<Tweet>::class.java).toList().filter { it.error == null && it.unknownError == null }
            }
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