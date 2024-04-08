package com.thoughtworks.helloworld_view.retrofit

import com.thoughtworks.helloworld_view.room.entity.Tweet
import retrofit2.http.GET


interface ApiService {
    @GET("TW-Android-Junior-Training/android_training_practice/main/json/tweets.json")
    suspend fun getJsonFile(): List<Tweet>
}