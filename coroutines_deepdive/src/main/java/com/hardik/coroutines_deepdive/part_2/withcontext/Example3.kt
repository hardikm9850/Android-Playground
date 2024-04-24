package com.hardik.coroutines_deepdive.part_2.withcontext

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * When we use withContext(SupervisorJob()), then withContext is still using a regular Job,
 * and the SupervisorJob() becomes its parent. As a result, when one child raises an exception,
 * the other children will be cancelled as well. withContext will also throw an exception,
 * so its SupervisorJob() is practically useless.
 */
suspend fun main() = coroutineScope {
    println("Before")
    withContext(SupervisorJob()) { //
        launch {
            delay(1000)
            throw Error()
        }
        launch {
            delay(2000)
            println("Done")
        }
    }
    println("After")
}