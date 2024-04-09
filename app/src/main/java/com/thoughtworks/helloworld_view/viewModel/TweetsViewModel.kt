package com.thoughtworks.helloworld_view.viewModel

import androidx.lifecycle.ViewModel
import com.thoughtworks.helloworld_view.dataSource.TweetDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    tweetDataSource: TweetDataSource
) : ViewModel() {
    val tweetsLiveData = tweetDataSource.fetchTweets()
}