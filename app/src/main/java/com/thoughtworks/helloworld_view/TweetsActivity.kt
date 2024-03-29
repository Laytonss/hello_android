package com.thoughtworks.helloworld_view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.thoughtworks.helloworld_view.adapters.TweetAdapter
import com.thoughtworks.helloworld_view.model.Sender
import com.thoughtworks.helloworld_view.model.Tweet

const val IS_USE_JSON_FILE_FEATURE_TOGGLE = true

class TweetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
        initUI()
    }

    private fun initUI() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val bottomTextView = findViewById<TextView>(R.id.bottom_text_id)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val tweetAdapter = TweetAdapter(getTweetListData())
        recyclerView.adapter = tweetAdapter
        configBottomHintMessage(recyclerView, bottomTextView)
    }

    private fun configBottomHintMessage(recyclerView: RecyclerView, bottomTextView: TextView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = recyclerView.adapter!!.itemCount
                    if (lastVisibleItemPosition == totalItemCount - 1) {
                        bottomTextView.visibility = TextView.VISIBLE
                    } else {
                        bottomTextView.visibility = TextView.INVISIBLE
                    }
                }
            }
        })
    }

    private fun getTweetListData(): List<Tweet> {
        if (!IS_USE_JSON_FILE_FEATURE_TOGGLE) {
            return arrayListOf(
                Tweet("content1", emptyList(), Sender("name1", "nick1", "avatar1"), emptyList()),
                Tweet("content2", emptyList(), Sender("name2", "nick1", "avatar1"), emptyList()),
                Tweet("content3", emptyList(), Sender("name3", "nick1", "avatar1"), emptyList()),
                Tweet("content4", emptyList(), Sender("name4", "nick1", "avatar1"), emptyList()),
                Tweet("content5", emptyList(), Sender("name5", "nick1", "avatar1"), emptyList()),
                Tweet("content6", emptyList(), Sender("name6", "nick1", "avatar1"), emptyList()),
            )
        }
        val gson = Gson()
        val inputStream = resources.openRawResource(R.raw.tweets)
        val json = inputStream.bufferedReader().readText()
        return gson.fromJson(json, Array<Tweet>::class.java).toList().filter { it.error == null && it.unknownError == null }
    }
}