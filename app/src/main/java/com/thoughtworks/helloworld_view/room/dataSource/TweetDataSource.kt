package com.thoughtworks.helloworld_view.room.dataSource

import android.content.res.Resources
import android.util.Log
import com.google.gson.Gson
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.room.entity.Tweet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TweetDataSource(private val application: MyApplication) {

    private val tweetDao = application.getTweetDao()
    private val gson = Gson()

    fun fetchTweets(): Flow<List<Tweet>> {
        return tweetDao.getAll()
    }

    fun insertDataFromJsonFile() {
        application.resources.openRawResource(R.raw.tweets).use { inputStream ->
            val json = inputStream.bufferedReader().readText()
            val tweetList = gson.fromJson(json, Array<Tweet>::class.java).toList()
            Log.d("room", "$tweetList")
            MainScope().launch(Dispatchers.IO) {
                tweetDao.insertALL(tweetList)
            }
        }
    }
}