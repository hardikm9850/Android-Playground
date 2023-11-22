package com.hardik.playground.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main(args: Array<String>) = runBlocking {
    var child1: Job? = null

    coroutineScope {
        val job = GlobalScope.launch {
            child1 = launch {
                repeat(1000) {
                    Thread.sleep(1000)
                    print("1")
                    yield()
                }
            }

            repeat(1000) {
                delay(1000)
                print("0")
            }
        }
        delay(4000)
        job?.cancelAndJoin()
        println()

        println("job is cancelled: ${job.isCancelled}")
        println("job is active: ${job.isActive}")
        job.join()
    }
    println("coroutineScope finished")
}
