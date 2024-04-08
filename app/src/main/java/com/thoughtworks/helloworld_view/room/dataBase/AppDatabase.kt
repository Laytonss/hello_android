package com.thoughtworks.helloworld_view.room.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thoughtworks.helloworld_view.room.converts.TweetDataConverter
import com.thoughtworks.helloworld_view.room.dao.TweetDao
import com.thoughtworks.helloworld_view.room.entity.Comment
import com.thoughtworks.helloworld_view.room.entity.Image
import com.thoughtworks.helloworld_view.room.entity.Sender
import com.thoughtworks.helloworld_view.room.entity.Tweet

@Database(
    entities = [Tweet::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TweetDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao
}