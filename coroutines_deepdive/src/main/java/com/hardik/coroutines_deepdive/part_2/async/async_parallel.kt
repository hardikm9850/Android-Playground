package com.hardik.coroutines_deepdive.part_2.async

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    // This will be executed in parallel
    launch {
        val news = async {
            getNews()
        }
        // We could wrap it with async but it would be redundant
        val newsSummary = getNewsSummary()
        showNews(newsSummary,news.await())
    }

    // This will be executed sequentially
    launch {
        val news = getNews()
        val newsSummary = getNewsSummary()
        showNews(newsSummary,news)
    }
}

fun showNews(newsSummary: List<String>, news: List<String>) {
    println(news)
    println(newsSummary)
}


suspend fun getNews(): List<String> {
    println("getting news")
    delay(3000)
    println("news is ready")
    return listOf("news list")
}

suspend fun getNewsSummary(): List<String> {
    println("getting news summary")
    delay(5000)
    println("news summary is ready")
    return listOf("news summary")
}

//getting news summary
//getting news
//news is ready
//news summary is ready
//[news list]
//[news summary]

