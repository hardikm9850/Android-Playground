package com.hardik.playground.coroutine

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import java.lang.Exception

val exception = CoroutineExceptionHandler { coroutineContext, throwable ->
    println("\n Exception caught $throwable")
}

val scope = CoroutineScope(Job() + exception)

fun main(args: Array<String>) = runBlocking() {
    val job = launch(exception) {
        supervisorScope {

            launch {
                doWork(1)
            }
            launch {
                doWork(2)
            }
            launch {
                delay(2000)
                throw Exception()
            }
        }

    }

    job.join()
}

suspend fun doWork(int: Int) {
    while (true) {
        print(int)
        delay(100)
    }
}