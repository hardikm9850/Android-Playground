package com.hardik.coroutines_deepdive.part_2.supervisor_scope

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

suspend fun main(): Unit = supervisorScope {
    launch {
        delay(3000)
        println("print me too ")
    }
    launch {
        delay(1000)
        println("print me ")
    }
    launch {
        delay(300)
        println("Error ")
        throw IllegalStateException()
    }
}