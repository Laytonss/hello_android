package com.thoughtworks.helloworld_view.retrofit

import retrofit2.http.GET


interface ApiService {
    @GET("TW-Android-Junior-Training/android_training_practice/main/json/tweets.json")
    fun getJsonFile(): String
}