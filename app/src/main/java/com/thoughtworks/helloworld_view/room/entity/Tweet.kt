package com.thoughtworks.helloworld_view.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Tweet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val content: String? = null,
    val images: List<Image>? = null,
    @Embedded val sender: Sender? = null,
    val comments: List<Comment>? = null,
    val error: String? = null,
    @SerializedName("unknown error")
    val unknownError: String? = null
)