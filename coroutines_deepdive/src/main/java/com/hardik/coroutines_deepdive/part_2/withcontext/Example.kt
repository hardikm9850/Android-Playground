package com.hardik.coroutines_deepdive.part_2.withcontext

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
// ```withContext``` Calls the specified suspending block with a given coroutine context,
// suspends until it completes, and returns the result.

fun CoroutineScope.log(text: String) {
    val name = this.coroutineContext[CoroutineName]?.name
    println("[$name] $text")
}

fun main() = runBlocking(CoroutineName("Parent")) {
    log("Before")
    withContext(CoroutineName("Child 1")) {
        // suspends until it completes, and returns the result.
        delay(1000)
        log("Hello 1")
        delay(3000)
    }
    coroutineScope {
        delay(1000)
        log("Hello 2")
    }
    withContext(CoroutineName("Child 2")) {
        delay(2000)
        log("Hello 3")
    }
    log("After")
}

//[Parent] Before
//[Child 1] Hello 1
//[Parent] Hello 2
//[Child 1] Hello 1
//[Parent] After