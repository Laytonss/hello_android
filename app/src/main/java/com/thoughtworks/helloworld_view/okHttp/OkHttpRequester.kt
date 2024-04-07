package com.thoughtworks.helloworld_view.okHttp

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.executeAsync
import java.io.IOException

class OkHttpRequester {
    private val client: OkHttpClient = OkHttpClient()

    suspend fun request(url: String): String {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        val call = client.newCall(request)
        val response = call.executeAsync()
        if (!response.isSuccessful) throw IOException("Unexpected code ${response.code}")
        return response.body.string()
    }
}