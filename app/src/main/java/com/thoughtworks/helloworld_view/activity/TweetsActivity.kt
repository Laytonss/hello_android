package com.thoughtworks.helloworld_view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.adapters.TweetAdapter
import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.room.entity.Comment
import com.thoughtworks.helloworld_view.room.entity.Image
import com.thoughtworks.helloworld_view.room.entity.Sender
import com.thoughtworks.helloworld_view.room.entity.Tweet
import com.thoughtworks.helloworld_view.room.model.TweetData
import kotlinx.coroutines.runBlocking


class TweetsActivity : AppCompatActivity(R.layout.tweets_layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val tweetAdapter = TweetAdapter(getTweetListData())
        recyclerView.adapter = tweetAdapter
    }

    private fun getTweetListData(): List<TweetData> {
        insertDataToDB()
        return getTweetDataFromDB()
    }

    private fun getTweetDataFromDB(): List<TweetData> {
        val application = applicationContext as MyApplication
        val tweetDao = application.getTweetDao()
        return runBlocking {
            val tweets = tweetDao.getAll()
            val tweetDataList = tweetDao.getTweetDataList()
            Log.d("room数据库查询结果", "tweetData个数:${tweetDataList.size} tweetData第一个结果${tweetDataList.first()}")
            tweetDataList.filter { it.tweet.error == null && it.tweet.unknownError == null }
        }
    }

    private fun insertDataToDB() {
        val application = applicationContext as MyApplication
        val tweetDao = application.getTweetDao()
        val senderDao = application.getSenderDao()
        val imageDao = application.getImageDao()
        val commentDao = application.getCommentDao()
        runBlocking {
            tweetDao.insertALL(arrayListOf(Tweet(1, "content1", "error", "un error")))
            senderDao.insertALL(arrayListOf(Sender(1, 1, "name")))
            imageDao.insertALL(arrayListOf(Image(1, 1, "url1"), Image(2, 1, "url2")))
            commentDao.insertALL(arrayListOf(Comment(1, 1, "comment content"), Comment(2, 1, "comment content")))

            tweetDao.insertALL(arrayListOf(Tweet(2, "content1", null, null)))
            senderDao.insertALL(arrayListOf(Sender(2, 2, "name")))
            imageDao.insertALL(arrayListOf(Image(3, 2, "url1"), Image(4, 1, "url2")))
            commentDao.insertALL(arrayListOf(Comment(3, 2, "comment content"), Comment(4, 1, "comment content")))

            tweetDao.insertALL(arrayListOf(Tweet(3, "content1", null, null)))
            senderDao.insertALL(arrayListOf(Sender(3, 3, "name")))
            imageDao.insertALL(arrayListOf(Image(5, 3, "url1"), Image(6, 1, "url2")))
            commentDao.insertALL(arrayListOf(Comment(5, 3, "comment content"), Comment(6, 1, "comment content")))
        }
    }
}