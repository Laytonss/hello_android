package com.thoughtworks.helloworld_view.room.dataSource

import com.google.gson.Gson
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.okHttp.OkHttpRequester
import com.thoughtworks.helloworld_view.retrofit.RetrofitClient
import com.thoughtworks.helloworld_view.room.entity.Tweet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


const val TWEET_URL = "https://raw.githubusercontent.com/TW-Android-Junior-Training/android_training_practice/main/json/tweets.json"

class TweetDataSource(private val application: MyApplication) {

    private val tweetDao = application.getTweetDao()
    private val gson = Gson()

    fun fetchTweets(): Flow<List<Tweet>> {
        return tweetDao.getAll()
    }

    fun getAndInsertDataToDB() {
        //val tweetList = getDataFromLocalJsonFile()
        MainScope().launch(Dispatchers.IO) {
            val tweetList = getDataFromRetrofit()
            tweetDao.insertALL(tweetList)
        }
    }

    //用Retrofit从network读数据
    private suspend fun getDataFromRetrofit(): List<Tweet> {
        return RetrofitClient.apiService.getJsonFile()
    }

    //用okHttp从network读数据
    private suspend fun getDataFromOkHttp(): List<Tweet> {
        val jsonFromNetwork = OkHttpRequester().request(TWEET_URL)
        return gson.fromJson(jsonFromNetwork, Array<Tweet>::class.java).toList()
    }

    //从json文件读tweet数据
    private fun getDataFromLocalJsonFile(): List<Tweet> {
        application.resources.openRawResource(R.raw.tweets).use { inputStream ->
            val json = inputStream.bufferedReader().readText()
            return gson.fromJson(json, Array<Tweet>::class.java).toList()
        }
    }
}