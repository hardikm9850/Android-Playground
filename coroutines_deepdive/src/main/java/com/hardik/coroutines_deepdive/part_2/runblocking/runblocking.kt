package com.hardik.coroutines_deepdive.part_2.runblocking

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * runBlocking is a very atypical builder.
 * It blocks the thread it has been started on whenever its coroutine is suspended(similar to suspending main).
 * This means that delay(1000L) inside runBlocking will behave like Thread.sleep(1000L).
 *
 * Using a dispatcher, we can make runBlocking run on a different thread.
 * But still, the thread on which this builder has been started will be blocked until the coroutine is done.
 */
fun main() {
    runBlocking {// it runs a new coroutine and blocks the current thread interruptibly until its completion.
        delay(1000L)
        println("World!")
    }
    runBlocking {
        delay(1000L)
        println("World!")
    }
    runBlocking {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}
// (1 sec)
// World!
// (1 sec)
// World!
// (1 sec)
// World!
// Hello,