package com.thoughtworks.helloworld_view.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Tweet(
    val content: String? = null,
    val images: List<Image>? = null,
    val sender: Sender? = null,
    val comments: List<Comment>? = null,
    val error: String? = null,
    @SerializedName("unknown error")
    val unknownError: String? = null
)