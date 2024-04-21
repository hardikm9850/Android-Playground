package com.hardik.coroutines_deepdive.part_2.exceptions.supervisor_job

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Supervisor Job overrides childCancelled method and returns false indicating that the parent coroutine will ignore any
// exception raised by it's children
/**
 *  private class SupervisorJobImpl(parent: Job?) : JobImpl(parent) {
 *     override fun childCancelled(cause: Throwable): Boolean = false
 * }
 */

fun main(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob())
    scope.launch {
        delay(1000)
        throw Error("Some error")
    }
    scope.launch {
        delay(2000)
        println("Will be printed")
    }
    delay(3000)
}
// O/P
//Exception in thread "DefaultDispatcher-worker-1" java.lang.Error: Some error
//at com.hardik.coroutines_deepdive.part_2.exceptions.SupervisorJobExampleKt$main$1$1.invokeSuspend(SupervisorJobExample.kt:13)
//at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
//at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:106)
//at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:570)
//at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:750)
//at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:677)
//at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:664)
//Suppressed: kotlinx.coroutines.DiagnosticCoroutineContextException: [StandaloneCoroutine{Cancelling}@7768edf7, Dispatchers.Default]
//Will be printed