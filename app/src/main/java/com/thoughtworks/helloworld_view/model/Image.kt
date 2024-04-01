package com.thoughtworks.helloworld_view.model

class Image(var url: String) {
    fun setUrl(url: String): Image {
        this.url = url
        return this
    }
}
