package com.hardik.playground.coroutine

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    println("error occurred $throwable")
}

fun main() = runBlocking<Unit>(exceptionHandler) {
    launch {
        executeFlow().collect {
            println("A value received $it")
        }
    }
    delay(5000)
    launch {
        executeFlow().collect {
            println("B value received $it")
        }
    }

 }


fun executeFlow() = flow {
    var value = 1
    while (true) {
        emit(value++)
        delay(400)
    }
}