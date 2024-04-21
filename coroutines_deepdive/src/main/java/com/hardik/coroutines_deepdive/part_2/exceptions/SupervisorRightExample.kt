package com.hardik.coroutines_deepdive.part_2.exceptions

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job = SupervisorJob()
    launch(job) { // 1
        launch(job) {
            delay(1000)
            throw Error("Some error")
        }
        launch(job) {
            delay(2000)
            println("Will be printed")
        }
    }
    job.complete()
    job.join()
    launch(job) {
        println("Will not be printed since job is completed")
    }
}
//Exception in thread "main" java.lang.Error: Some error
//at com.hardik.coroutines_deepdive.part_2.exceptions.SupervisorRightExampleKt$main$1$1$1.invokeSuspend(SupervisorRightExample.kt:14)
//at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
//at kotlinx.coroutines.DispatchedTaskKt.resume(DispatchedTask.kt:234)
//at kotlinx.coroutines.DispatchedTaskKt.dispatch(DispatchedTask.kt:166)
//at kotlinx.coroutines.CancellableContinuationImpl.dispatchResume(CancellableContinuationImpl.kt:397)
//at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl(CancellableContinuationImpl.kt:431)
//at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl$default(CancellableContinuationImpl.kt:420)
//at kotlinx.coroutines.CancellableContinuationImpl.resumeUndispatched(CancellableContinuationImpl.kt:518)
//at kotlinx.coroutines.EventLoopImplBase$DelayedResumeTask.run(EventLoop.common.kt:500)
//at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:284)
//at kotlinx.coroutines.BlockingCoroutine.joinBlocking(Builders.kt:85)
//at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking(Builders.kt:59)
//at kotlinx.coroutines.BuildersKt.runBlocking(Unknown Source)
//at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(Builders.kt:38)
//at kotlinx.coroutines.BuildersKt.runBlocking$default(Unknown Source)
//at com.hardik.coroutines_deepdive.part_2.exceptions.SupervisorRightExampleKt.main(SupervisorRightExample.kt:9)
//at com.hardik.coroutines_deepdive.part_2.exceptions.SupervisorRightExampleKt.main(SupervisorRightExample.kt)
//Suppressed: kotlinx.coroutines.DiagnosticCoroutineContextException: [StandaloneCoroutine{Cancelling}@51c8530f, BlockingEventLoop@7403c468]
//Will be printed
//
//Process finished with exit code 0
