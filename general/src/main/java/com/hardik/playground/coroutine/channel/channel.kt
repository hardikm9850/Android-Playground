package com.hardik.playground.coroutine.channel

import com.hardik.playground.exercise.channel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val localChannel = Channel<Int>()


fun main() = runBlocking {
    //createChannel(CoroutineScope(Job()))
    //createChannelWithRepeat(CoroutineScope(Job()))
    createChannelWithLoop(CoroutineScope(Job()))
    // here we print five received integers:

}

suspend fun createChannel(scope: CoroutineScope) {
    val channel = Channel<Int>()
    scope.launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) {
            channel.send(x * x)
        }
        channel.close()
    }
}

suspend fun createChannelWithRepeat(scope: CoroutineScope) {
    val channel = Channel<Int>()
    scope.launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) {
            channel.send(x * x)
        }
        channel.close()
    }
// here we print five received integers:
    repeat(5) { println(channel.receive()) }
    println("Done!")
}
suspend fun createChannelWithLoop(scope: CoroutineScope) {
    scope.launch {
        println("## 1 ## ${Thread.currentThread().name}")
        for (x in 1..5) {
            localChannel.send(x * x)
            delay(1000)
            println("## 2")
        }
        println("## 3")
        localChannel.close() // we're done sending
    }
    println("## 4")
    for (y in localChannel) println(y)
    println("Done! ")

    // here we print received values using `for` loop (until the channel is closed)
}

