package com.thoughtworks.helloworld_view.room.converts

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.helloworld_view.room.entity.Comment
import com.thoughtworks.helloworld_view.room.entity.Image

class TweetDataConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromJsonToImages(value: String?): List<Image>? {
        return value?.let {
            val listType = object : TypeToken<List<Image>>() {}.type
            gson.fromJson(it, listType)
        }
    }

    @TypeConverter
    fun fromImagesToJson(images: List<Image>?): String? {
        return images?.let {
            gson.toJson(it)
        }
    }

    @TypeConverter
    fun fromJsonToComments(value: String?): List<Comment>? {
        return value?.let {
            val listType = object : TypeToken<List<Comment>>() {}.type
            gson.fromJson(it, listType)
        }
    }

    @TypeConverter
    fun fromCommentsToJson(comments: List<Comment>?): String? {
        return comments?.let {
            gson.toJson(it)
        }
    }
}