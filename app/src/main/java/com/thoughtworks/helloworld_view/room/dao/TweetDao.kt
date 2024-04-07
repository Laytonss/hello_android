package com.thoughtworks.helloworld_view.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.helloworld_view.room.entity.Tweet
import kotlinx.coroutines.flow.Flow

@Dao
interface TweetDao {
    @Query("SELECT * FROM Tweet")
    fun getAll(): Flow<List<Tweet>>

    @Insert
    fun insertALL(tweets: List<Tweet>)
}