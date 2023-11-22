package com.hardik.playground.coroutine // ktlint-disable filename

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.runBlocking

fun executeFlow1() = flow {
    var value = 1
    while (true) {
        emit(value++)
        delay(400)
    }
}

fun main(args: Array<String>) = runBlocking<Unit> {
    executeFlow1().shareIn(this, started = SharingStarted.WhileSubscribed()).collect {
    }
}
