package com.thoughtworks.helloworld_view.room.model

import androidx.room.Embedded
import androidx.room.Relation
import com.thoughtworks.helloworld_view.room.entity.Comment
import com.thoughtworks.helloworld_view.room.entity.Image
import com.thoughtworks.helloworld_view.room.entity.Sender
import com.thoughtworks.helloworld_view.room.entity.Tweet

data class TweetData(
    @Embedded val tweet: Tweet,

    @Relation(
        parentColumn = "tweetId",
        entityColumn = "tweetOwnerId"
    )
    val sender: Sender,

    @Relation(
        parentColumn = "tweetId",
        entityColumn = "tweetOwnerId"
    )
    val images: List<Image>,

    @Relation(
        parentColumn = "tweetId",
        entityColumn = "tweetOwnerId"
    )
    val comments: List<Comment>,
)