package com.thoughtworks.helloworld_view.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.thoughtworks.helloworld_view.room.entity.Tweet
import com.thoughtworks.helloworld_view.viewModel.TweetData
import kotlinx.coroutines.flow.Flow

@Dao
interface TweetDao {
    @Query("SELECT * FROM Tweet")
    fun getAll(): List<Tweet>

    @Insert
    fun insertALL(tweets: List<Tweet>)

    @Transaction
    @Query("SELECT * FROM Tweet")
    fun getTweetDataList(): Flow<List<TweetData>>
}