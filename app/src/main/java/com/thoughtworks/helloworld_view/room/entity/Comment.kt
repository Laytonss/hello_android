package com.thoughtworks.helloworld_view.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    @PrimaryKey val commentId: Int,
    val tweetOwnerId: Int,
    val content: String,
//    val sender: Sender
)
