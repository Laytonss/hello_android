package com.thoughtworks.helloworld_view.room.dataSource

import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.room.entity.Tweet
import kotlinx.coroutines.flow.Flow

class TweetDataSource(application: MyApplication) {

    private val tweetDao = application.getTweetDao()

    fun fetchTweets(): Flow<List<Tweet>> {
        insertDataFromJsonFile()
        return tweetDao.getAll()
    }

    private fun insertDataFromJsonFile() {

    }
}