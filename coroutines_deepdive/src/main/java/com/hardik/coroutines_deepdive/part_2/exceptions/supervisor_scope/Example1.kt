package com.hardik.coroutines_deepdive.part_2.exceptions.supervisor_scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

/**
 * supervisorScope is just a suspending function and can be used to wrap suspending function bodies.
 *
 * Supervisor scope overrides childCancelled method and returns false indicating that the parent coroutine will ignore any
 * exception raised by it's children
 * ```
 * private class SupervisorCoroutine<in T>(
 *     context: CoroutineContext,
 *     uCont: Continuation<T>
 * ) : ScopeCoroutine<T>(context, uCont) {
 *     override fun childCancelled(cause: Throwable): Boolean = false
 * }
 * ```
 * The provided scope inherits its coroutineContext from the outer scope, but overrides context's Job with SupervisorJob.
 *
 * This is very convenient as we still keep a connection to the parent, yet any exceptions from the coroutine will be silenced.
 *
 */
fun main(): Unit = runBlocking {
    val job = supervisorScope {
        launch {
            delay(1000)
            throw Error("Some error")
        }
        launch {
            delay(2000)
            println("Will be printed")
        }
    }
    job.join()
    println("Done")
}