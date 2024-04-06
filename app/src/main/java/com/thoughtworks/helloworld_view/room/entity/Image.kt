package com.thoughtworks.helloworld_view.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Image(
    @PrimaryKey val imageId: Int,
    val tweetOwnerId: Int,
    val url: String
)
