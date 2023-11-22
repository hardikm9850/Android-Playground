package com.hardik.playground.coroutine.work

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Serial execution example
 */
fun main() = runBlocking {
    val urls = listOf(
        "https://dummyjson.com/products/1",
        "https://dummyjson.com/products/2",
        "https://dummyjson.com/products/3",
    )
    for (url in urls) {
        val result = async { fetchJson(url) }.await()
        println("$url -> $result")
    }
}

fun fetchJson(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()
    val response = client.newCall(request).execute()
    return response.body?.string() ?: ""
}
