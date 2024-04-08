package com.thoughtworks.helloworld_view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.dataSource.TweetDataSource

class TweetsViewModel(application: Application) : AndroidViewModel(application) {

    private val tweetDataSource = TweetDataSource(application as MyApplication)

    val tweetsLiveData = tweetDataSource.fetchTweets()
}