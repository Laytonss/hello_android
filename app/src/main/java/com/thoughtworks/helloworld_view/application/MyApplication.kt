package com.thoughtworks.helloworld_view.application

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.thoughtworks.helloworld_view.room.dao.TweetDao
import com.thoughtworks.helloworld_view.room.dataBase.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
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
}