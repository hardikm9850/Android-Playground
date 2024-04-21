package com.hardik.coroutines_deepdive.part_2.job

import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) { // the new job replaces one from parent
        delay(1000)
        println("Text 1")
    }
    launch(job) { // the new job replaces one from parent
        delay(2000)
        println("Text 2")
    }
    // ============== Parent job will not finish ==============
    //job.join()
    //println("Will not be printed") because the job will remain in Active state even when all its children are finished.
    // This is because this context is still ready to be used by other coroutines.

    // ============== Joining on child jobs ==============
    //job.children.forEach { it.join() }

    // ============== Job complete - used to complete a job  ==============
    //Once it is used, all the child coroutines will keep running until they are all done, but new coroutines cannot be started in this job
    job.complete()
    job.join()
}
