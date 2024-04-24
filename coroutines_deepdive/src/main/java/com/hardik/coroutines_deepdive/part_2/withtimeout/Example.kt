package com.hardik.coroutines_deepdive.part_2.withtimeout

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.yield

suspend fun fetchUser(): String { // Runs forever
    delay(100000)
    return "name"
}

suspend fun getUserOrNull(): String? = withTimeoutOrNull(5000) {
    fetchUser()
}
suspend fun main(): Unit = coroutineScope {
    val user = getUserOrNull()
    println("User: $user")
}
// (5 sec)
// User: null
