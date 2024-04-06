package com.thoughtworks.helloworld_view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.adapters.TweetAdapter
import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.room.entity.Comment
import com.thoughtworks.helloworld_view.room.entity.Image
import com.thoughtworks.helloworld_view.room.entity.Sender
import com.thoughtworks.helloworld_view.room.entity.Tweet
import kotlinx.coroutines.runBlocking

const val IS_USE_JSON_FILE_FEATURE_TOGGLE = false
const val IS_USE_ASSET_FILE_FEATURE_TOGGLE = false
const val IS_USE_ROOM_DATA_FEATURE_TOGGLE = true

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
        if (IS_USE_ROOM_DATA_FEATURE_TOGGLE) {
            insertDataToDB()
            return getTweetDataFromDB()

        }
        return arrayListOf(
//            Tweet("content1", emptyList(), Sender("name1", "nick1", "avatar1"), emptyList()),
//            Tweet("content2", emptyList(), Sender("name2", "nick1", "avatar1"), emptyList()),
//            Tweet("content3", emptyList(), Sender("name3", "nick1", "avatar1"), emptyList()),
//            Tweet("content4", emptyList(), Sender("name4", "nick1", "avatar1"), emptyList()),
//            Tweet("content5", emptyList(), Sender("name5", "nick1", "avatar1"), emptyList()),
//            Tweet("content6", emptyList(), Sender("name6", "nick1", "avatar1"), emptyList()),
        )
    }

    private fun getTweetDataFromDB(): List<Tweet> {
        val application = applicationContext as MyApplication
        val tweetDao = application.getTweetDao()
        val senderDao = application.getSenderDao()
        return runBlocking {
            val tweets = tweetDao.getAll()
            val tweetDataList = tweetDao.getTweetDataList()
            Log.d("room", "### ${tweetDataList.size} ${tweetDataList.first()}")
            tweets.filter { it.error == null && it.unknownError == null }
        }
    }

    private fun insertDataToDB() {
        val application = applicationContext as MyApplication
        val tweetDao = application.getTweetDao()
        val senderDao = application.getSenderDao()
        val imageDao = application.getImageDao()
        val commentDao = application.getCommentDao()
        runBlocking {
            senderDao.insertALL(arrayListOf(Sender(1, 1, "name")))
            tweetDao.insertALL(arrayListOf(Tweet(1, "content1", "error", "un error")))
            imageDao.insertALL(arrayListOf(Image(1, 1, "url1"), Image(2, 1, "url2")))
            commentDao.insertALL(arrayListOf(Comment(1, 1, "comment content"),Comment(2, 1, "comment content")))
        }
    }
}