package com.thoughtworks.helloworld_view.model

import com.google.gson.annotations.SerializedName

class Tweet {
    var content: String? = null
        private set
    var images: List<Image>? = null
        private set
    var sender: Sender? = null
        private set
    var comments: List<Comment>? = null
        private set
    var error: String? = null

    @SerializedName("unknown error")
    var unknownError: String? = null

    constructor()
    constructor(content: String?, images: List<Image>?, sender: Sender?, comments: List<Comment>?) {
        this.content = content
        this.images = images
        this.sender = sender
        this.comments = comments
    }

    fun setContent(content: String?): Tweet {
        this.content = content
        return this
    }

    fun setImages(images: List<Image>?): Tweet {
        this.images = images
        return this
    }

    fun setSender(sender: Sender?): Tweet {
        this.sender = sender
        return this
    }

    fun setComments(comments: List<Comment>?): Tweet {
        this.comments = comments
        return this
    }

    override fun toString(): String {
        return "content $content \n"
    }
}
