package com.hardik.playground.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun produceNumbers(coroutineScope: CoroutineScope): ReceiveChannel<Int> =
    coroutineScope.produce {
        var x = 1
        while (true) {
            send(x++)
            delay(1500)
        }
    }

suspend fun main(args: Array<String>) {
    var producer: ReceiveChannel<Int>? = null
    val scope = CoroutineScope(Job())
    scope.launch {
        producer = produceNumbers(this)
    }
    delay(100 )
    repeat(5) {
        consumer(it, scope, producer!!)
    }
    delay(15000)
    producer?.cancel()
}

suspend fun consumer(index: Int, coroutineScope: CoroutineScope,producer: ReceiveChannel<Int>) {
    coroutineScope.launch {
        producer.consumeEach {
            println("$index received here $it")
        }
    }
}