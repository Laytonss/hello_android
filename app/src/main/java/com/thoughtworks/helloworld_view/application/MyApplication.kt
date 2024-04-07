package com.thoughtworks.helloworld_view.application

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.thoughtworks.helloworld_view.room.dao.TweetDao
import com.thoughtworks.helloworld_view.room.dataBase.AppDatabase

class MyApplication : Application() {
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my-database"
        ).build()
        Log.d("room", "database init finish")
    }

    override fun onTerminate() {
        super.onTerminate()
        database.close()
    }

    fun getTweetDao(): TweetDao {
        return database.tweetDao()
    }

    fun getSenderDao(): SenderDao {
        return database.senderDao()
    }

    fun getImageDao(): ImageDao {
        return database.imageDao()
    }

    fun getCommentDao(): CommentDao {
        return database.commentDao()
    }
}