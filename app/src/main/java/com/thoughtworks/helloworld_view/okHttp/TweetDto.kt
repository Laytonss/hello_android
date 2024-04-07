package com.thoughtworks.helloworld_view.okHttp

import com.google.gson.annotations.SerializedName


data class TweetDto(
    val content: String? = null,
    val images: List<Image>? = null,
    val sender: Sender? = null,
    val comments: List<Comment>? = null,
    val error: String? = null,
    @SerializedName("unknown error")
    val unknownError: String? = null
)