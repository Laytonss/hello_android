package com.thoughtworks.helloworld_view.room.dataSource

import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.room.entity.Comment
import com.thoughtworks.helloworld_view.room.entity.Image
import com.thoughtworks.helloworld_view.room.entity.Sender
import com.thoughtworks.helloworld_view.room.entity.Tweet
import com.thoughtworks.helloworld_view.viewModel.TweetData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TweetDataSource(application: MyApplication) {

    private val tweetDao = application.getTweetDao()
    private val senderDao = application.getSenderDao()
    private val imageDao = application.getImageDao()
    private val commentDao = application.getCommentDao()

    fun fetchTweets(): Flow<List<TweetData>> {
        insertDataToDB()
        return tweetDao.getTweetDataList()
    }

    private fun insertDataToDB() {
        MainScope().launch(Dispatchers.IO) {
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