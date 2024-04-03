package com.thoughtworks.helloworld_view.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.helloworld_view.room.entity.Tweet

@Dao
interface TweetDao {
    @Query("SELECT * FROM Tweet")
    suspend fun getAll(): List<Tweet>

    @Insert
    suspend fun insertALL(tweets: List<Tweet>): Int
}