package com.thoughtworks.helloworld_view.utils

import com.google.gson.Gson
import com.thoughtworks.helloworld_view.room.entity.Sender
import com.thoughtworks.helloworld_view.room.entity.Tweet

private fun generateTweetListJson() {
    val gson = Gson()
    val tweetListData = arrayListOf(
        Tweet("content1", emptyList(), Sender("name1", "nick1", "avatar1"), emptyList()),
        Tweet("content2", emptyList(), Sender("name2", "nick1", "avatar1"), emptyList()),
        Tweet("content3", emptyList(), Sender("name3", "nick1", "avatar1"), emptyList()),
        Tweet("content4", emptyList(), Sender("name4", "nick1", "avatar1"), emptyList()),
        Tweet("content5", emptyList(), Sender("name5", "nick1", "avatar1"), emptyList()),
        Tweet(null, emptyList(), Sender("name6", "nick1", "avatar1"), emptyList()),
        Tweet(null, emptyList(), Sender("name7", "nick1", "avatar1"), emptyList()),
        Tweet("content string very big content string very big content string very big content string very big", emptyList(), Sender("name", "nick1", "avatar1"), emptyList()),
        Tweet("content last", emptyList(), Sender("name", "nick1", "avatar1"), emptyList()),
    )
    val errorTweet = Tweet("content8", emptyList(), Sender("name8", "nick1", "avatar1"), emptyList(), "error")
    tweetListData.add(errorTweet)

    val unKnowErrorTweet = Tweet("content9", emptyList(), Sender("name9", "nick1", "avatar1"), emptyList(), null, "unknownError")
    tweetListData.add(unKnowErrorTweet)
    println(gson.toJson(tweetListData))
}

fun main() {
    println(generateTweetListJson())
}