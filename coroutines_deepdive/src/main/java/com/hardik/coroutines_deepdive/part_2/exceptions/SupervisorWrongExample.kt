package com.hardik.coroutines_deepdive.part_2.exceptions

import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    // Don't do that, SupervisorJob with one children
    // and no parent works similar to just Job
    val job = SupervisorJob()
    launch(job) { // 1
        println("outer " + coroutineContext[Job])
        launch {
            println("inner 1 " + coroutineContext[Job])
            delay(1000)
            throw Error("Some error")
        }
        launch {
            println("inner 2 " + coroutineContext[Job])
            delay(2000)
            println("Will not be printed")
        }
    }
    job.join()
    delay(3000)
}