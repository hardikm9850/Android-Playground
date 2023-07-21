package com.hardik.playground.coroutine.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay

suspend fun main() {
    produceSquares(CoroutineScope(Job())).consumeEach {
        println("received $it")
    }
}

fun produceSquares(scope: CoroutineScope): ReceiveChannel<Int> {
    return scope.produce {
        for(item in  1..5){
            delay(2000)
            send(item)
        }
    }
}