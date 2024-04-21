package com.hardik.coroutines_deepdive.part_2.exceptions

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    try {
        launch {
            delay(1000)
            throw IllegalStateException("Some error")
        }
    } catch (e: Throwable) {
        println("Caught exception outside coroutine: $e")
    }

    launch {
        delay(2000)
        println("This will not be printed")
    }
}
// This will throw an exception