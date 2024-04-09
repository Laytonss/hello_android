package com.thoughtworks.helloworld_view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.thoughtworks.helloworld_view.dataSource.TweetDataSource
import com.thoughtworks.helloworld_view.room.entity.Image
import com.thoughtworks.helloworld_view.room.entity.Sender
import com.thoughtworks.helloworld_view.room.entity.Tweet
import com.thoughtworks.helloworld_view.viewModel.TweetsViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class TweetViewModelTest {

    @Mock
    lateinit var mockTweetDataSource: TweetDataSource

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `should return same live data when mock fetchTweets method`() {
        val mockTweetLiveDataList = buildTweetLiveData(
            arrayListOf(
                Tweet(1, "content1", arrayListOf(Image("url1")), Sender("username1")),
                Tweet(2, "content2", arrayListOf(Image("url2")), Sender("username2")),
            )
        )
        `when`(mockTweetDataSource.fetchTweets()).thenReturn(mockTweetLiveDataList)
        val tweetsViewModel = TweetsViewModel(mockTweetDataSource)
        assertEquals(mockTweetLiveDataList, tweetsViewModel.tweetsLiveData)
    }

    private fun buildTweetLiveData(tweetList: List<Tweet>): MutableLiveData<List<Tweet>> {
        val mockTweetLiveDataList = MutableLiveData<List<Tweet>>()
        mockTweetLiveDataList.value = tweetList
        return mockTweetLiveDataList
    }
}