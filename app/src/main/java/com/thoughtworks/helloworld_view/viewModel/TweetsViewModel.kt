package com.thoughtworks.helloworld_view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.thoughtworks.helloworld_view.application.MyApplication
import com.thoughtworks.helloworld_view.dataSource.TweetDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    val tweetDataSource: TweetDataSource
) : ViewModel() {
    val tweetsLiveData = tweetDataSource.fetchTweets()
}