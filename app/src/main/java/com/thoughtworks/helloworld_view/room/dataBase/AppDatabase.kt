package com.thoughtworks.helloworld_view.room.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thoughtworks.helloworld_view.room.dao.TweetDao
import com.thoughtworks.helloworld_view.room.entity.Tweet

@Database(entities = [Tweet::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao
}