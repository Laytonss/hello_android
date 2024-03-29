package com.thoughtworks.helloworld_view

import com.google.gson.Gson
import com.thoughtworks.helloworld_view.model.Sender
import com.thoughtworks.helloworld_view.model.Tweet
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun generateTweetListJson() {
        val gson = Gson()
        val tweetListData = arrayListOf(
            Tweet("content1", emptyList(), Sender("name1", "nick1", "avatar1"), emptyList()),
            Tweet("content2", emptyList(), Sender("name2", "nick1", "avatar1"), emptyList()),
            Tweet("content3", emptyList(), Sender("name3", "nick1", "avatar1"), emptyList()),
            Tweet("content4", emptyList(), Sender("name4", "nick1", "avatar1"), emptyList()),
            Tweet("content5", emptyList(), Sender("name5", "nick1", "avatar1"), emptyList()),
            Tweet("content6", emptyList(), Sender("name6", "nick1", "avatar1"), emptyList()),
        )
        println(gson.toJson(tweetListData))
    }
}