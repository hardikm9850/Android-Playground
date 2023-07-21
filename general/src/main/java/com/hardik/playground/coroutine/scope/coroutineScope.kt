package com.hardik.playground.coroutine.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

suspend fun main() = failMeToo()

suspend fun failMe(){
    val job = CoroutineScope(Job()).launch { // child
        try {
            println("hi")
            throw IOException("Test")
        } catch(e: Throwable) {
            // handle failure
            println("bye $e")
        }
    }
    job.join()
}

suspend fun failMeToo(){
    println("11")

    GlobalScope.launch {
        println("1")
        val run = async(Dispatchers.IO) {
            if (System.currentTimeMillis() != 0L) {
                throw IOException("Test")
            }
            "foobar"
        }
        println("2")

        try {
            val res = run.await()
            println("result ")
        } catch (x: Throwable) {
            println("failed $x")
        }
    }
}
suspend fun computeWithCoroutineScope(): String = coroutineScope {
    val supervisorJob = SupervisorJob()

    val color = async(supervisorJob) { delay(3_000); "purple" }
    val height = async<Double>(supervisorJob) { delay(1_000); throw IOException() }
    val job = launch(SupervisorJob()) {
        delay(100)
        throw IOException()
    }
    job.join()
    try {
        return@coroutineScope "A %s box %.1f inches tall".format(color.await(), height.await())
    } catch (e: Exception) {
        println("exception $e")
    }
    return@coroutineScope "failed"
}

suspend fun computeWithSupervisorScope(): String = coroutineScope {
    val supervisorJob = SupervisorJob()

    val color = async(supervisorJob) { delay(3_000); "purple" }
    val height = async<Double>(supervisorJob) { delay(1_000); throw IOException() }
    val job = launch {
        delay(100)
        throw IOException()
    }
    job.join()
    try {
        return@coroutineScope "A %s box %.1f inches tall".format(color.await(), height.await())
    } catch (e: Exception) {
        println("exception $e")
    }
    return@coroutineScope "failed"
}