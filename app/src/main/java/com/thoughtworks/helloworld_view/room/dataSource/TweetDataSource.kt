package com.thoughtworks.helloworld_view.room.dataSource

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

    fun insertDataToDB() {
        val tweetList = getDataFromLocalJsonFile()
        MainScope().launch(Dispatchers.IO) {
            tweetDao.insertALL(tweetList)
        }
    }

    private fun getDataFromLocalJsonFile(): List<Tweet> {
        application.resources.openRawResource(R.raw.tweets).use { inputStream ->
            val json = inputStream.bufferedReader().readText()
            return gson.fromJson(json, Array<Tweet>::class.java).toList()
        }
    }
}