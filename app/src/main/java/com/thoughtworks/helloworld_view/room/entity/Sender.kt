package com.thoughtworks.helloworld_view.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sender(
    @PrimaryKey var senderId: Int,
    val tweetOwnerId: Int,
    val userName: String? = null,
    val nick: String? = null,
    val avatar: String? = null,
)
