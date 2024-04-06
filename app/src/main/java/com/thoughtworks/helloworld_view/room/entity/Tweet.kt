package com.thoughtworks.helloworld_view.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Tweet(
    @PrimaryKey var tweetId: Int,
    val content: String? = null,
    val error: String? = null,
    @SerializedName("unknown error")
    val unknownError: String? = null
)