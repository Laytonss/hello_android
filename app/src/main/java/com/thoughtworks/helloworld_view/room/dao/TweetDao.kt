package com.thoughtworks.helloworld_view.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.thoughtworks.helloworld_view.room.entity.Tweet
import com.thoughtworks.helloworld_view.room.model.TweetData

@Dao
interface TweetDao {
    @Query("SELECT * FROM Tweet")
    suspend fun getAll(): List<Tweet>

    @Insert
    suspend fun insertALL(tweets: List<Tweet>)

    @Transaction
    @Query("SELECT * FROM Tweet")
    suspend fun getTweetDataList(): List<TweetData>
}