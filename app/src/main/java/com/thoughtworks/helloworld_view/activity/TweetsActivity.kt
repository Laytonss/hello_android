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

    private fun getTweetListData(): List<Tweet> {
        insertDataToDB()
        return getTweetDataFromDB()
    }

    private fun getTweetDataFromDB(): List<Tweet> {
        val application = applicationContext as MyApplication
        val tweetDao = application.getTweetDao()
        return runBlocking {
            val tweets = tweetDao.getAll()
            val tweetDataList = tweetDao.getTweetDataList()
            Log.d("room", "### ${tweetDataList.size} ${tweetDataList.first()}")
            tweets.filter { it.error == null && it.unknownError == null }
        }
    }

    private fun insertDataToDB() {
        val application = applicationContext as MyApplication
        val tweetDao = application.getTweetDao()
        val senderDao = application.getSenderDao()
        val imageDao = application.getImageDao()
        val commentDao = application.getCommentDao()
        runBlocking {
            senderDao.insertALL(arrayListOf(Sender(1, 1, "name")))
            tweetDao.insertALL(arrayListOf(Tweet(1, "content1", "error", "un error")))
            imageDao.insertALL(arrayListOf(Image(1, 1, "url1"), Image(2, 1, "url2")))
            commentDao.insertALL(arrayListOf(Comment(1, 1, "comment content"), Comment(2, 1, "comment content")))
        }
    }
}