package com.hardik.coroutines_deepdive.part_2.withcontext

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

suspend fun main() {
    showSomeData()
}

suspend fun showSomeData() = coroutineScope {
    val data = async(Dispatchers.IO) {
        return@async foo()
    }

    withContext(Dispatchers.Default) {
        doSomeWork()
        val result = data.await()
        display(result)
    }
}

fun display(result: Int) {
    println("result is $result")
}

suspend fun doSomeWork() {
    println("some work")
    delay(500)
    println("work done")
}

suspend fun foo() : Int{
    println("api call started")
    delay(3500)
    println("api call done")
    return 10
}