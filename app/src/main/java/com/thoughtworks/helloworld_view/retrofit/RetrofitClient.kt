package com.thoughtworks.helloworld_view.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TWEET_URL = "https://raw.githubusercontent.com/"

object RetrofitClient {

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(TWEET_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}