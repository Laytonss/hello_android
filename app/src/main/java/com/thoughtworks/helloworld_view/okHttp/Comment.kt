package com.thoughtworks.helloworld_view.okHttp

import com.thoughtworks.helloworld_view.room.entity.Sender

data class Comment(
    val content: String,
    val sender: Sender
)