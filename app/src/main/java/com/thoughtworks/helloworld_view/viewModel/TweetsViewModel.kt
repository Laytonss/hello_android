package com.thoughtworks.helloworld_view.viewModel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.dataSource.TweetDataSource

class TweetsViewModel(application: MyApplication) : AndroidViewModel(application) {

    private val tweetDataSource = TweetDataSource(application)

    val tweetsLiveData = tweetDataSource.fetchTweets()
}