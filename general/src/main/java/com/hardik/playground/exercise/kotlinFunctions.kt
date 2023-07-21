package com.hardik.playground.exercise

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import java.lang.StringBuilder

fun reverseString() {
    val str = "abcd"
    val builder = StringBuilder()
    for (i in str.length - 1 downTo 0) {
        builder.append(str[i])
    }
    println(builder.toString())
}

suspend fun main() {
    createCoroutineChannel()
}

val channel = Channel<String>()


suspend fun createCoroutineChannel() {
    val channel = Channel<Int>()
    CoroutineScope(Job()).launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) channel.send(x * x)
    }
// here we print five received integers:
    println(channel.receive())
    //repeat(5) {  }
    println("Done!")
}