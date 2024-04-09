package com.thoughtworks.helloworld_view.di

import android.content.Context
import androidx.room.Room
import com.thoughtworks.helloworld_view.dataSource.TweetDataSource
import com.thoughtworks.helloworld_view.room.dao.TweetDao
import com.thoughtworks.helloworld_view.room.dataBase.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "my-database"
        ).build()
    }

    @Provides
    fun provideTweetDao(database: AppDatabase): TweetDao = database.tweetDao()

    @Provides
    fun provideDataSource(@ApplicationContext context: Context, tweetDao: TweetDao): TweetDataSource = TweetDataSource(context, tweetDao)
}